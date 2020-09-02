package com.sj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromptlyOrderVO {
    private List<AnimeOrderVO> animeOrderVOList;
    private List<Map<String,String>> option;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String username;
    private String tel;
    private String userAddress;
    private String orderId;
    private long time;
    private String cause;
}
