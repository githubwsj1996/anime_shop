package com.sj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressListVO {
    @JsonProperty(value = "id")
    private Integer user_addressid;
    @JsonProperty(value = "tel")
    private String user_phone;
    @JsonProperty(value = "address")
    private String user_address;
    private String name;
}
