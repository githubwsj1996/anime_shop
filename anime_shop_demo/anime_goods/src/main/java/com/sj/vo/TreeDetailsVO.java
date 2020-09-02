package com.sj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeDetailsVO {
    @JsonProperty("id")
    private Integer specs_id;
    @JsonProperty("name")
    private String  specs_name;
    @JsonProperty("imgUrl")
    private String specs_icon;
    @JsonProperty("previewImgUrl")
    private String specs_preview;
}
