package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCart {
    private String cart_id;
    private UserInfo userInfo;
    private AnimeInfo animeInfo;
    private AnimeSpecs animeSpecs;
    private Integer quantity;
    private Integer checked;
}
