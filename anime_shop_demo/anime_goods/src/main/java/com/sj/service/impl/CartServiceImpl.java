package com.sj.service.impl;

import com.sj.dto.UserCartDto;
import com.sj.entity.AnimeInfo;
import com.sj.entity.AnimeSpecs;
import com.sj.entity.UserCart;
import com.sj.entity.UserCartSave;
import com.sj.repository.GoodsRepository;
import com.sj.repository.GoodsSpecsRepository;
import com.sj.repository.UserCartRepository;
import com.sj.service.CartService;
import com.sj.until.KeyUtil;
import com.sj.until.ResultUtil;
import com.sj.vo.ResultVO;
import com.sj.vo.UserCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserCartRepository userCartRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsSpecsRepository goodsSpecsRepository;

    @Override
    public ResultVO saveCart(UserCartDto userCartDto) {
        Integer userId = userCartDto.getUserId();
        Integer specsId = userCartDto.getSpecsId();
        Integer quantity = userCartDto.getQuantity();

        UserCartSave userCartSave = new UserCartSave();
        userCartSave.setCart_id(KeyUtil.createId());
        userCartSave.setAnime_id(userCartDto.getAnimeId());
        userCartSave.setUser_id(userCartDto.getUserId());
        userCartSave.setSpecs_id(userCartDto.getSpecsId());
        userCartSave.setQuantity(userCartDto.getQuantity());
        userCartSave.setChecked(0);

        UserCartSave userCartSave1 = userCartRepository.checkUserCart(userId, specsId);
        int result;
        if (userCartSave1 != null) {
            result = userCartRepository.updateUserCart(quantity, userId, specsId);
        } else {
            result = userCartRepository.saveUserCart(userCartSave);
        }
        goodsRepository.updateByGoods(userCartDto.getAnimeId(), quantity);
        goodsSpecsRepository.updateSpecs(specsId, quantity);
        if (result == 1) {
            return ResultUtil.success("添加购物城成功");
        } else {
            return ResultUtil.error("数据异常");
        }
    }

    @Override
    public ResultVO countUserCart(Integer userId) {
        return ResultUtil.success(userCartRepository.countUserCart(userId));
    }

    @Override
    public ResultVO findByUserCartId(Integer userId) {
        List<UserCart> userCartList = userCartRepository.findCartByUserId(userId);

        List<UserCartVO> userCartVOList = userCartList.stream()
                .map(e -> new UserCartVO(
                        e.getCart_id(),
                        e.getAnimeInfo().getAnime_desc(),
                        e.getAnimeSpecs().getSpecs_price() + ".00",
                        e.getAnimeSpecs().getSpecs_name(),
                        e.getAnimeSpecs().getSpecs_icon(),
                        e.getQuantity(),
                        e.getChecked() == 0 ? false : true,
                        e.getQuantity() + e.getAnimeSpecs().getSpecs_stock()
                )).collect(Collectors.toList());

        return ResultUtil.success(userCartVOList);
    }

    @Override
    public ResultVO addQuantity(String cartId) {
        int result;
        result = userCartRepository.updateCartPlus(cartId, 1);

        if (result != 0) {
            UserCart userCart = userCartRepository.findByCartId(cartId);
            System.out.println(userCart);
            goodsRepository.updateByGoods(userCart.getAnimeInfo().getAnime_id(), 1);
            goodsSpecsRepository.updateSpecs(userCart.getAnimeSpecs().getSpecs_id(), 1);
            Integer animeStock = goodsRepository.findAllById(userCart.getAnimeInfo().getAnime_id()).getAnime_stock();
            Integer specsStock = goodsSpecsRepository.findAllById(userCart.getAnimeSpecs().getSpecs_id()).getSpecs_stock();
            if (animeStock == 0 || specsStock == 0) {
                return ResultUtil.error("当先商品最大购买数量！");
            }
            return ResultUtil.success("添加商品成功!");
        }
        return ResultUtil.error("添加商品失败！");
    }

    @Override
    public ResultVO reduceQuantity(String cartId) {
        int result;
        result = userCartRepository.updateCartPlus(cartId, -1);

        if (result != 0) {
            UserCart userCart = userCartRepository.findByCartId(cartId);
            goodsRepository.updateByGoods(userCart.getAnimeInfo().getAnime_id(), -1);
            goodsSpecsRepository.updateSpecs(userCart.getAnimeSpecs().getSpecs_id(), -1);

            Integer quantity = userCart.getQuantity();
            if (quantity == 1) {
                return ResultUtil.error("至少添加一件商品！");
            }
            return ResultUtil.success("减少商品成功!");
        }
        return ResultUtil.error("添加商品失败！");
    }

    @Override
    public ResultVO deleteUserCart(List<Map<String, String>> cartValues) {
        List<String> carIdList=new ArrayList<>();
        List<AnimeInfo>  animeInfoList=new ArrayList<>();
        List<AnimeSpecs> animeSpecsList=new ArrayList<>();
        AnimeInfo animeInfo=null;
        AnimeSpecs animeSpecs=null;

        for (Map<String, String> cartValue : cartValues) {
            animeInfo=new AnimeInfo();
            animeSpecs=new AnimeSpecs();

            animeInfo.setAnime_id(userCartRepository.findByCartId(cartValue.get("cartId")).getAnimeInfo().getAnime_id());
            animeInfo.setAnime_stock(Integer.parseInt(cartValue.get("quantity")));
            animeInfoList.add(animeInfo);

            animeSpecs.setSpecs_id(userCartRepository.findByCartId(cartValue.get("cartId")).getAnimeSpecs().getSpecs_id());
            animeSpecs.setSpecs_stock(Integer.parseInt(cartValue.get("quantity")));
            animeSpecsList.add(animeSpecs);

            carIdList.add(cartValue.get("cartId"));
        }
        goodsRepository.updateAnimeStock(animeInfoList);
        goodsSpecsRepository.updateSpecsStock(animeSpecsList);
        userCartRepository.deleteUserCart(carIdList);

        return ResultUtil.success("删除成功！");
    }
}
