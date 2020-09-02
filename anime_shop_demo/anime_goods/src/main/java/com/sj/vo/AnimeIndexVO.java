package com.sj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeIndexVO {
    public List<AnimeInfoVO> animeInfoVOList;
    public int countPages;
    public List<Map<String,String>> categories;
}
