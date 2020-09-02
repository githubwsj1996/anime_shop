package com.sj.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CartVO {
    List<UserCartVO> userCartVOList;
    List<Map<String,Boolean>> list;
}
