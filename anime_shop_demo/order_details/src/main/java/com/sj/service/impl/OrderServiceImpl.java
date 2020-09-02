package com.sj.service.impl;

import com.sj.dto.OrderDto;
import com.sj.entity.*;
import com.sj.repository.*;
import com.sj.service.OrderService;
import com.sj.until.KeyUtil;
import com.sj.until.RedisUtil;
import com.sj.until.ResultUtil;
import com.sj.vo.AnimeOrderVO;
import com.sj.vo.PromptlyOrderVO;
import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailsRepository detailsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CanCelCauseRepository causeRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsSpecsRepository specsRepository;

    @Autowired
    private UserCartRepository userCartRepository;


    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    //立即购买
    @Override
    public ResultVO PromptlySaveOrder(OrderDto orderDto) {
        Integer quantity=orderDto.getQuantity();

        BigDecimal totalPrice=new BigDecimal(0);
        totalPrice=orderDto.getPrice().divide(new BigDecimal(100))
                .multiply(new BigDecimal(quantity))
                .add(totalPrice);
        String orderId=KeyUtil.createId();

        OrderDetailsSave detailsSave = new OrderDetailsSave();
        detailsSave.setOrder_details_id(KeyUtil.createId());
        detailsSave.setOrder_id(orderId);
        detailsSave.setUser_id(orderDto.getUserId());
        detailsSave.setAddress_id(orderDto.getAddressId());
        detailsSave.setAnime_id(orderDto.getAnimeId());
        detailsSave.setSpecs_id(orderDto.getSpecsId());
        detailsSave.setPrice(orderDto.getPrice());
        detailsSave.setQuantity(orderDto.getQuantity());
        detailsSave.setTotal_price(totalPrice);

        int result = detailsRepository.promptlySaveDetails(detailsSave);
        if(result==0){
            return ResultUtil.error("数据有误！");
        }else{
            Orders orders=new Orders();
            orders.setOrder_id(orderId);
            orders.setOrder_price(detailsRepository.sumTotalPrice(orderId).add(new BigDecimal("20")));
            result=ordersRepository.saveOrders(orders);
            if (result != 0) {
                goodsRepository.updateByGoods(orderDto.getAnimeId(),quantity);
                specsRepository.updateSpecs(orderDto.getSpecsId(),quantity);
                return  ResultUtil.success(orders.getOrder_id());
            } else {
                return ResultUtil.error("数据有误！");
            }
        }
    }


    /*查询购物清单*/
    @Override
    public ResultVO findPromptlySaveOrder(String orderId) {
        List<OrderDetails> orderDetailsList=detailsRepository.findPromptlyOrder(orderId);
        List<AnimeOrderVO> animeOrderVOList=orderDetailsList.stream()
                .map(e->new AnimeOrderVO(
                        e.getAnimeInfo().getAnime_desc(),
                        e.getAnimeSpecs().getSpecs_name(),
                        e.getAnimeSpecs().getSpecs_icon(),
                        e.getPrice().divide(new BigDecimal(100)),
                        e.getQuantity()
                )).collect(Collectors.toList());

        PromptlyOrderVO promptlyOrderVO=new PromptlyOrderVO();
        for (OrderDetails orderDetails : orderDetailsList) {
            promptlyOrderVO.setPrice(detailsRepository.sumTotalPrice(orderId));
            promptlyOrderVO.setTotalPrice(orderDetails.getOrdersLook().getOrder_price().multiply(new BigDecimal(100)));
            promptlyOrderVO.setUsername(orderDetails.getAddress().getAddress_name());
            promptlyOrderVO.setTel(orderDetails.getAddress().getUser_phone());
            promptlyOrderVO.setUserAddress(orderDetails.getAddress().getUser_address().replaceAll("/",""));
            promptlyOrderVO.setOrderId(orderDetails.getOrdersLook().getOrder_id());
            promptlyOrderVO.setTime(redisUtil.getExpire(orderDetails.getOrdersLook().getOrder_id()));
        }
        promptlyOrderVO.setAnimeOrderVOList(animeOrderVOList);

        return ResultUtil.success(promptlyOrderVO);
    }

    //批量创建购物车订单
    @Override
    public ResultVO cartSaveDetails(List<Map<String, String>> cartValues) {
        List<String> cartIdList=new ArrayList<>();
        String orderId=KeyUtil.createId();
        List<OrderDetailsSave> orderDetailsSaveList=new ArrayList<>();
        OrderDetailsSave orderDetailsSave=null;
        BigDecimal price=null;
        BigDecimal totalPrice=null;
        for (Map<String, String> cartValue : cartValues) {
            cartIdList.add(cartValue.get("cartId"));

            UserCart userCart=userCartRepository.findByCartId(cartValue.get("cartId"));
            orderDetailsSave=new OrderDetailsSave();
            orderDetailsSave.setOrder_details_id(KeyUtil.createId());
            orderDetailsSave.setOrder_id(orderId);
            orderDetailsSave.setUser_id(userCart.getUserInfo().getUser_id());
            orderDetailsSave.setAddress_id(Integer.parseInt(cartValue.get("addressId")));
            orderDetailsSave.setAnime_id(userCart.getAnimeInfo().getAnime_id());
            orderDetailsSave.setSpecs_id(userCart.getAnimeSpecs().getSpecs_id());
            String price1=cartValue.get("price");
            price=new BigDecimal(price1);
            orderDetailsSave.setPrice(price);
            int quantity=Integer.parseInt(cartValue.get("quantity"));
            orderDetailsSave.setQuantity(quantity);

            totalPrice=new BigDecimal(0);
            totalPrice=price.divide(new BigDecimal(100))
                    .multiply(new BigDecimal(quantity))
                    .add(totalPrice);
            orderDetailsSave.setTotal_price(totalPrice);

            orderDetailsSaveList.add(orderDetailsSave);
        }
        detailsRepository.cartSaveDetails(orderDetailsSaveList);

        Orders orders=new Orders();
        orders.setOrder_id(orderId);
        BigDecimal orderPrice=detailsRepository.sumTotalPrice(orderId).add(new BigDecimal("20"));
        orders.setOrder_price(orderPrice);
        ordersRepository.saveOrders(orders);

        userCartRepository.deleteUserCart(cartIdList);

        return ResultUtil.success(orderId);
    }

    /*订单状态查询订单*/
    @Override
    public ResultVO findByState(Integer userId, Integer state) {
        List<OrderDetails> orderDetailsList=detailsRepository.findNoByState(userId,state);
        PromptlyOrderVO promptlyOrderVO=null;
        List<PromptlyOrderVO> promptlyOrderVOList=new ArrayList<>();

        List<CancelCause> cancelCauseList=causeRepository.findAll();
        List<Map<String,String>> optionsList=new ArrayList<>();
        Map<String,String> option=null;
        for (CancelCause cancelCause : cancelCauseList) {
            option=new HashMap<>();
            option.put("text",cancelCause.getCause_text());
            option.put("value",cancelCause.getCause_id().toString());
            optionsList.add(option);
        }

        for (OrderDetails orderDetails : orderDetailsList) {
            promptlyOrderVO=new PromptlyOrderVO();
            promptlyOrderVO.setOption(optionsList);
            String orderId=orderDetails.getOrdersLook().getOrder_id();
            List<OrderDetails> detailsList=detailsRepository.findPromptlyOrder(orderId);
            List<AnimeOrderVO> animeOrderVOList=detailsList.stream()
                    .map(e->new AnimeOrderVO(
                            e.getAnimeInfo().getAnime_desc(),
                            e.getAnimeSpecs().getSpecs_name(),
                            e.getAnimeSpecs().getSpecs_icon(),
                            e.getPrice().divide(new BigDecimal(100)),
                            e.getQuantity()
                    )).collect(Collectors.toList());
            promptlyOrderVO.setAnimeOrderVOList(animeOrderVOList);

            promptlyOrderVO.setPrice(detailsRepository.sumTotalPrice(orderId));
            promptlyOrderVO.setTotalPrice(orderDetails.getOrdersLook().getOrder_price().multiply(new BigDecimal(100)));
            promptlyOrderVO.setUsername(orderDetails.getAddress().getAddress_name());
            promptlyOrderVO.setTel(orderDetails.getAddress().getUser_phone());
            promptlyOrderVO.setUserAddress(orderDetails.getAddress().getUser_address().replaceAll("/",""));
            promptlyOrderVO.setOrderId(orderDetails.getOrdersLook().getOrder_id());
            promptlyOrderVO.setTime(redisUtil.getExpire(orderDetails.getOrdersLook().getOrder_id()));
            promptlyOrderVO.setCause(orderDetails.getOrdersLook().getCancelCause().getCause_text());
            promptlyOrderVOList.add(promptlyOrderVO);
        }
        return ResultUtil.success(promptlyOrderVOList);
    }

    /*修改订单状态*/
    @Override
    public ResultVO updateState(String orderId, Integer state) {
        int result=ordersRepository.updateStateSaveCause(orderId,state,8);
        redisUtil.del(orderId);
        if(result!=0){
            return  ResultUtil.success("支付成功！");
        }
        return ResultUtil.error("支付失败！");
    }

    /*获得未支付剩余的时间*/
    @Override
    public ResultVO getExpire(String orderId) {

        redisTemplate.opsForValue().set(orderId,0);
        redisUtil.expire(orderId,60);
        return ResultUtil.success(redisUtil.getExpire(orderId));
    }

    /*未支付倒计时完成，修改订单状态，更新库存*/
    @Override
    public ResultVO delExpireOrder(String orderId) {
        List<OrderDetails> orderDetailsList=detailsRepository.findDetailsByState(orderId,0);
        List<AnimeInfo> animeInfoList=new ArrayList<>();
        List<AnimeSpecs> animeSpecsList=new ArrayList<>();
        AnimeInfo animeInfo=null;
        AnimeSpecs animeSpecs=null;
        for (OrderDetails orderDetails : orderDetailsList) {
            animeInfo=new AnimeInfo();
            animeInfo.setAnime_id(orderDetails.getAnimeInfo().getAnime_id());
            animeInfo.setAnime_stock(orderDetails.getQuantity());
            animeInfoList.add(animeInfo);

            animeSpecs=new AnimeSpecs();
            animeSpecs.setSpecs_id(orderDetails.getAnimeSpecs().getSpecs_id());
            animeSpecs.setSpecs_stock(orderDetails.getQuantity());
            animeSpecsList.add(animeSpecs);
        }
        //修改商品和规格的库存
        goodsRepository.updateMoreStock(animeInfoList);
        specsRepository.updateMoreStock(animeSpecsList);
        //修改订单状态！
        ordersRepository.updateStateSaveCause(orderId,2,7);

        //删除缓存
        redisUtil.del(orderId);

        return ResultUtil.success("订单："+orderId+"，已过期！");
    }

    /*用户更新取消订单原因，更新库存*/
    @Override
    public ResultVO cancelOrder(String orderId, String causeId) {
        int result=ordersRepository.updateStateSaveCause(orderId,2,Integer.parseInt(causeId));

        if(result!=0){
            List<OrderDetails> orderDetailsList=detailsRepository.findPromptlyOrder(orderId);
            List<AnimeInfo> animeInfoList=new ArrayList<>();
            List<AnimeSpecs> animeSpecsList=new ArrayList<>();
            AnimeInfo animeInfo=null;
            AnimeSpecs animeSpecs=null;
            for (OrderDetails orderDetails : orderDetailsList) {
                animeInfo=new AnimeInfo();
                animeInfo.setAnime_id(orderDetails.getAnimeInfo().getAnime_id());
                animeInfo.setAnime_stock(orderDetails.getQuantity());
                animeInfoList.add(animeInfo);

                animeSpecs=new AnimeSpecs();
                animeSpecs.setSpecs_id(orderDetails.getAnimeSpecs().getSpecs_id());
                animeSpecs.setSpecs_stock(orderDetails.getQuantity());
                animeSpecsList.add(animeSpecs);
            }
            //修改商品和规格的库存
            goodsRepository.updateMoreStock(animeInfoList);
            specsRepository.updateMoreStock(animeSpecsList);

            //删除缓存
            redisUtil.del(orderId);
            return  ResultUtil.success("成功取消订单！");
        }
        return ResultUtil.error("取消订单失败！");
    }

    @Override
    public ResultVO deleteOrder(String orderId) {
        int result1=detailsRepository.deleteDetails(orderId);
        int result2=ordersRepository.deleteOrder(orderId);
        if(result1!=0&&result2!=0){
            return  ResultUtil.success("订单已成功删除！");
        }
        return ResultUtil.error("订单删除失败");
    }

    @Override
    public ResultVO countDetailsByAnimeId() {
        List<OrderDetails> orderDetailsList=detailsRepository.countDetailsByAnimeId();
        List<AnimeOrderVO> animeOrderVOList=new ArrayList<>();
        AnimeOrderVO animeOrderVO=null;

        for (OrderDetails orderDetails : orderDetailsList) {
            animeOrderVO=new AnimeOrderVO();
            animeOrderVO.setTitle(orderDetails.getAnimeInfo().getAnime_name());
            animeOrderVO.setPrice(orderDetails.getAnimeInfo().getAnime_price());
            animeOrderVOList.add(animeOrderVO);
        }
        return ResultUtil.success(animeOrderVOList);
    }
}
