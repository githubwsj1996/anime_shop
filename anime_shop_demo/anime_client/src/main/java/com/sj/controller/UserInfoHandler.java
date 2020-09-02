package com.sj.controller;

import com.sj.dto.AddressDto;
import com.sj.dto.UserDTO;
import com.sj.dto.UserInfoDto;
import com.sj.entity.UserInfo;
import com.sj.feign.UploadFeign;
import com.sj.feign.UserInfoFeign;
import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/UserInfo")
public class UserInfoHandler {

    @Autowired
    private UserInfoFeign userInfoFeign;

    @Autowired
    private UploadFeign uploadFeign;

    @PostMapping("/login")
    public ResultVO login(@RequestBody UserInfo user){
        return userInfoFeign.login(user);
    }

    @GetMapping("/checkName/{username}")
    public ResultVO checkName(@PathVariable("username") String username){
        return userInfoFeign.checkName(username);
    }

    @PostMapping("/register")
    public ResultVO registerUser(@RequestBody UserDTO userDTO){
        return userInfoFeign.registerUser(userDTO);
    }

    @PutMapping("/loginOut/{username}")
    public ResultVO  loginOut(@PathVariable("username") String username){
        return  userInfoFeign.loginOut(username);
    }

    @GetMapping("/findAddressList/{userId}")
    private ResultVO findAllAddress(@PathVariable("userId") Integer userId){
        return  userInfoFeign.findAllAddress(userId);
    }

    @PutMapping("/updateUserAddressId/{userId}/{addressId}")
    public ResultVO updateUserAddressId(@PathVariable("userId") Integer userId,
                                        @PathVariable("addressId") Integer addressId)

    {
        return  userInfoFeign.updateUserAddressId(userId,addressId);
    }

    @PostMapping("/saveAddress")
    public ResultVO saveAddress(@RequestBody AddressDto addressDto){
        return userInfoFeign.saveAddress(addressDto);
    }

    @GetMapping("/findByAddressId/{addressId}")
    public  ResultVO findByAddressId(@PathVariable("addressId") Integer addressId){
        return  userInfoFeign.findByAddressId(addressId);
    }

    @PutMapping("/updateAddress")
    public  ResultVO updateAddress(@RequestBody AddressDto addressDto){
        return userInfoFeign.updateAddress(addressDto);
    }

    @DeleteMapping("/deleteAddress/{userId}/{addressId}/{a}")
    public ResultVO deleteAddress(@PathVariable("userId") Integer userId,
                                  @PathVariable("addressId") Integer addressId,
                                  @PathVariable("a") Integer a){
        return userInfoFeign.deleteAddress(userId,addressId,a);
    }

    /*==========================用户信息操作================================*/
    @GetMapping("/findByUid/{userId}")
    public ResultVO findByUid(@PathVariable("userId") Integer userId){
        return  userInfoFeign.findByUid(userId);
    }

    @PostMapping("/uploadIcon")
    public ResultVO uploadIcon(MultipartFile file){
        return  uploadFeign.uploadIcon(file);
    }

    @PutMapping("/updateUserInfo")
    public ResultVO updateUserInfo(@RequestBody UserInfoDto userInfoDto){
        return  userInfoFeign.updateUserInfo(userInfoDto);
    }
}
