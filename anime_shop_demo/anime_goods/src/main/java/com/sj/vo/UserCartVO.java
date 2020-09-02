package com.sj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCartVO {
    private String cartId;
    private String title;
    private String price;
    private String desc;
    private String thumb;
    private Integer quantity;
    private Boolean check;
    private Integer maxQuantity;
}
