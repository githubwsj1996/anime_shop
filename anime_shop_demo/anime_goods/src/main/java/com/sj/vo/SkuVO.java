package com.sj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {
    private List<TreeVO> tree;
    private List<SkuListVO> list;
    private String price;
    private Integer stock_num;
    private Boolean none_sku=false;
    private Boolean hide_stock=false;
}
