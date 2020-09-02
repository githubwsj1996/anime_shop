package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsSave {
    private String order_details_id;
    private String order_id;
    private Integer user_id;
    private Integer address_id;
    private Integer anime_id;
    private Integer specs_id;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total_price;
}
