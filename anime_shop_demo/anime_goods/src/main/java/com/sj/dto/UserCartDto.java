package com.sj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCartDto {
    private String  cartId;
    private Integer userId;
    private Integer animeId;
    private Integer specsId;
    private Integer quantity;
}
