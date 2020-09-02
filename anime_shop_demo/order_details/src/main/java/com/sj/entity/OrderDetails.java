package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private String order_details_id;
    private OrdersLook ordersLook;
    private UserInfo userInfo;
    private UserAddress address;
    private AnimeInfo animeInfo;
    private AnimeSpecs animeSpecs;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total_price;
}
