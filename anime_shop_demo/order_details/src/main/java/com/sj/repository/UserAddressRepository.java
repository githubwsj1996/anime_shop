package com.sj.repository;

import com.sj.entity.UserAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressRepository {
    public List<UserAddress> findAllById(Integer addressId);
}
