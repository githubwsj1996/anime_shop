package com.sj.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Integer id;
    private Integer userId;
    private String name;
    private String tel;
    //省
    private String province;
    //市
    private String city;
    //区/县
    private String county;
    //详细地址
    private String addressDetail;
    //地区编码
    private Integer areaCode;
}
