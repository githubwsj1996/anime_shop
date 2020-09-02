package com.sj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private BigDecimal price;
    private Integer userId;
    private Integer addressId;
    private Integer animeId;
    private Integer specsId;
    private Integer quantity;
}
