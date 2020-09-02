package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCartSave {
    private String cart_id;
    private Integer user_id;
    private Integer anime_id;
    private Integer specs_id;
    private Integer quantity;
    private Integer checked;
}
