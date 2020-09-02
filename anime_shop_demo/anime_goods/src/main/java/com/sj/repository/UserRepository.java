package com.sj.repository;


import com.sj.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public UserInfo findAllByUserId(Integer userId);
}
