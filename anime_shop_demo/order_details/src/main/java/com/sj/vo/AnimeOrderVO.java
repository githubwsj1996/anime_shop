package com.sj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeOrderVO {
    private String title;
    private String desc;
    private String thumb;
    private BigDecimal price;
    private Integer quantity;
}
