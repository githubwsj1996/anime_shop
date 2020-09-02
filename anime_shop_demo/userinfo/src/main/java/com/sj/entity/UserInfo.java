package com.sj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserInfo {
    private Integer user_id;
    private String user_name;
    private String user_salt;
    private String user_password;
    private String user_icon;
    private Integer user_addressid;
    private Integer state;
    private String user_role;
    private String user_perm;

}
