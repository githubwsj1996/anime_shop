package com.sj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeVO {
    private String k="规格";
    private List<TreeDetailsVO> v;
    private String k_s="s1";
}
