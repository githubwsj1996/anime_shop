package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersLook {
    private String order_id;
    private BigDecimal order_price;
    private Integer order_state;
    private CancelCause cancelCause;
}
