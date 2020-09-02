package com.sj.service;

import com.sj.dto.UserCartDto;
import com.sj.vo.ResultVO;

import java.util.List;
import java.util.Map;

public interface CartService {
    //添加购物车信息
    public ResultVO saveCart(UserCartDto userCartDto);
    //统计用户购物车总数
    public ResultVO countUserCart(Integer userId);
    //查询用户购物车消息
    public ResultVO findByUserCartId(Integer userId);
    //购物车添加商品数量
    public ResultVO addQuantity(String cartId);
    //购物车减少商品数量
    public ResultVO reduceQuantity(String cartId);
    //批量删除购物车并批量修改商品库存和规格库存
    public ResultVO deleteUserCart(List<Map<String,String>> cartValues);
}
