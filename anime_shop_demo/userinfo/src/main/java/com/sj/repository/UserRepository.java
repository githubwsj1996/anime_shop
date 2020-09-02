package com.sj.repository;


import com.sj.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public UserInfo findAllByUserName(String username);
    public int updateUserState(String username,Integer state);
    public int registerUser(UserInfo user);
    public UserInfo findAllById(Integer userId);
    public int updateUserAddressId(Integer userId,Integer addressId);
    public int updateUserInfo1(UserInfo userInfo);
    public int updateUserInfo2(UserInfo userInfo);
}
