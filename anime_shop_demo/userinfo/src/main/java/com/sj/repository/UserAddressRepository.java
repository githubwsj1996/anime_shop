package com.sj.repository;

import com.sj.entity.AddressSave;
import com.sj.entity.UserAddress;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository {
    public List<UserAddress> findAllById(Integer userId);
    public int saveAddress(AddressSave addressSave);
    public UserAddress findByAddressId(Integer addressId);
    public int updateAddress(AddressSave addressSave);
    public int deleteAddress(Integer addressId);
}
