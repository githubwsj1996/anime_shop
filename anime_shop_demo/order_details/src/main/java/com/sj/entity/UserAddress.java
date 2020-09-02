package com.sj.entity;

import lombok.Data;

@Data
public class UserAddress {
    private Integer user_addressid;
    private Integer user_id;
    private String address_name;
    private String user_phone;
    private String user_address;
    private Integer user_area;
}
