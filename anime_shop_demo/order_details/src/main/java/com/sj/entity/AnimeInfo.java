package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeInfo {
    private Integer anime_id;
    private String anime_name;
    private Integer category_id;
    private BigDecimal anime_price;
    private String anime_desc;
    private Integer anime_stock;
    private String anime_icon;
    private String anime_tag;
    private Integer admin_id;
}
