package com.sj.repository;

import com.sj.entity.UserCart;
import com.sj.entity.UserCartSave;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCartRepository {
    //查询所有购物车
    public List<UserCart> findCartByUserId(Integer userId);
    //统计购物车数据行数
    public int countUserCart(Integer userId);
    //检查商品是否存在
    public UserCartSave checkUserCart(Integer userId,Integer specsId);
    //修改购物车原有物品的数量
    public int updateUserCart(Integer quantity,Integer userId,Integer specsId);
    //购物车添加新商品
    public int saveUserCart(UserCartSave userCartSave);
    //购物车按钮控制商品数量
    public int updateCartPlus(String cartId,Integer quantity);
    //通过cartId查询商品和规格的库存
    public UserCart findByCartId(String cartId);
    //批量删除购物车
    public void deleteUserCart(List<String> CartIdList);
}
