package com.sj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {
    private String username;
    private String password;
    private List<Map<String,String>> uploader;
}
