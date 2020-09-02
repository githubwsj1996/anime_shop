package com.sj.service;

import com.sj.dto.OrderDto;
import com.sj.entity.OrderDetails;
import com.sj.vo.ResultVO;

import java.util.List;
import java.util.Map;

public interface OrderService {
    //立即购买
    public ResultVO PromptlySaveOrder(OrderDto orderDto);
    /*查询购物清单*/
    public ResultVO findPromptlySaveOrder(String orderId);
    //批量创建购物车订单
    public ResultVO cartSaveDetails(List<Map<String,String>> cartValues);
    /*订单状态查询订单*/
    public ResultVO findByState(Integer userId,Integer state);
    /*修改订单状态*/
    public ResultVO updateState(String  orderId,Integer state);
    /*获得未支付剩余的时间*/
    public ResultVO getExpire(String orderId);
    /*未支付倒计时完成，修改订单状态，更新库存*/
    public ResultVO delExpireOrder(String orderId);
    /*用户更新取消订单原因，更新库存*/
    public ResultVO cancelOrder(String orderId,String causeId);
    /*删除取消的订单*/
    public ResultVO deleteOrder(String orderId);
    /*统计成交前三的商品*/
    public ResultVO countDetailsByAnimeId();

}
