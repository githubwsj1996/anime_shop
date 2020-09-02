package com.sj.feign;

import com.sj.dto.AddressDto;
import com.sj.dto.UserDTO;
import com.sj.dto.UserInfoDto;
import com.sj.entity.UserInfo;
import com.sj.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "user" )
public interface UserInfoFeign {

    @PostMapping("/User/login")
    public ResultVO login(@RequestBody UserInfo user);

    @PostMapping("/User/registerUser")
    public ResultVO registerUser(@RequestBody UserDTO userDTO);

    @GetMapping("/User/checkName/{username}")
    public ResultVO checkName(@PathVariable("username") String username);

    @PutMapping("/User/loginOut/{username}")
    public ResultVO  loginOut(@PathVariable("username") String username);

    @GetMapping("/UserInfo/findByUid/{userId}")
    public ResultVO findByUid(@PathVariable("userId") Integer userId);

    @PutMapping("/UserInfo/updateUserInfo")
    public ResultVO updateUserInfo(@RequestBody UserInfoDto userInfoDto);


    /*==========================地址操作================================*/
    @GetMapping("/UserInfo/findAddressList/{userId}")
    public ResultVO findAllAddress(@PathVariable("userId") Integer userId);

    @PutMapping("/UserInfo/updateUserAddressId/{userId}/{addressId}")
    public ResultVO updateUserAddressId(@PathVariable("userId") Integer userId,
                                        @PathVariable("addressId") Integer addressId);

    @PostMapping("/UserInfo/saveAddress")
    public ResultVO saveAddress(@RequestBody AddressDto addressDto);

    @GetMapping("/UserInfo/findByAddressId/{addressId}")
    public  ResultVO findByAddressId(@PathVariable("addressId") Integer addressId);

    @PutMapping("/UserInfo/updateAddress")
    public  ResultVO updateAddress(@RequestBody AddressDto addressDto);

    @DeleteMapping("/UserInfo/deleteAddress/{userId}/{addressId}/{a}")
    public ResultVO deleteAddress(@PathVariable("userId") Integer userId,
                                  @PathVariable("addressId") Integer addressId,
                                  @PathVariable("a") Integer a);



}
