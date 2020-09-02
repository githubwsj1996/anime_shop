package com.sj.repository;

import com.sj.entity.UserCart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCartRepository {
    //通过cartId查询商品和规格的库存
    public UserCart findByCartId(String cartId);
    //批量删除购物车
    public void deleteUserCart(List<String> CartIdList);
}
