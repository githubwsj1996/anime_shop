package com.sj.controller;

import com.sj.dto.AddressDto;
import com.sj.dto.UserInfoDto;
import com.sj.result.ResultUtil;
import com.sj.service.UserInfoService;
import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/UserInfo")
public class UserInfoHandler {
    @Autowired
    private UserInfoService userInfoService;

    /*==========================用户信息操作================================*/
    @GetMapping("/findByUid/{userId}")
    public ResultVO findByUid(@PathVariable("userId") Integer userId){
        return  userInfoService.findByUserId(userId);
    }

    @PostMapping("/uploadIcon")
    public ResultVO uploadIcon(MultipartFile file){
        return userInfoService.uploadIcon(file);
    }

    @PutMapping("/updateUserInfo")
    public ResultVO updateUserInfo(@RequestBody UserInfoDto userInfoDto){
        return  userInfoService.updateUserInfo(userInfoDto);
    }

    /*==========================用户地址操作=====================================*/
    /*获取地址列表*/
    @GetMapping("/findAddressList/{userId}")
    private ResultVO findAllAddress(@PathVariable("userId") Integer userId){
       return userInfoService.findAllAddressList(userId);
    }

    /*选择地址id*/
    @PutMapping("/updateUserAddressId/{userId}/{addressId}")
    public ResultVO updateUserAddressId(@PathVariable("userId") Integer userId,
                                        @PathVariable("addressId") Integer addressId)
    {
        return  userInfoService.updateUserAddressId(userId,addressId);
    }

    /*添加新地址*/
    @PostMapping("/saveAddress")
    public ResultVO saveAddress(@RequestBody AddressDto addressDto){
        return  userInfoService.saveAddress(addressDto);
    }

    //addressId查询
    @GetMapping("/findByAddressId/{addressId}")
    public  ResultVO findByAddressId(@PathVariable("addressId") Integer addressId){
        return  userInfoService.findByAddressId(addressId);
    }

    //修改address
    @PutMapping("/updateAddress")
    public  ResultVO updateAddress(@RequestBody AddressDto addressDto){
        return  userInfoService.updateAddress(addressDto);
    }

    //删除address
    @DeleteMapping("/deleteAddress/{userId}/{addressId}/{a}")
    public ResultVO deleteAddress(@PathVariable("userId") Integer userId,
                                  @PathVariable("addressId") Integer addressId,
                                  @PathVariable("a") Integer a){
        return userInfoService.deleteAddress(userId,addressId,a);
    }


}
