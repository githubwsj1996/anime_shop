package com.sj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuListVO {
    @JsonProperty("s1")
    private String specs_id;
    @JsonProperty("price")
    private Integer specs_price;
    @JsonProperty("stock_num")
    private Integer specs_stock;
}
