package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {
    private Integer user_addressid;
    private UserInfo userInfo;
    private String address_name;
    private String user_phone;
    private String user_address;
    private Integer user_area;
}
