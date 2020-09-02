package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeSpecs {
    private Integer specs_id;
    private AnimeInfo animeInfo;
    private String  specs_name;
    private Integer specs_stock;
    private BigDecimal specs_price;
    private String specs_icon;
    private String specs_preview;
}
