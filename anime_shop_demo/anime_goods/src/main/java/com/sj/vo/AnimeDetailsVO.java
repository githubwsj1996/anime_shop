package com.sj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sj.entity.AnimeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDetailsVO {
    private Integer anime_id;
    @JsonProperty("title")
    private String anime_name;
    private AnimeCategory animeCategory;
    @JsonProperty("price")
    private String anime_price;
    @JsonProperty("desc")
    private String anime_desc;
    private Integer anime_stock;
    @JsonProperty("thumb")
    private List<Map<String,String>> anime_icon;
    @JsonProperty("tag")
    private List<Map<String,String>> anime_tag;
    @JsonProperty("count")
    private Object count;
    private Integer admin_id;
}
