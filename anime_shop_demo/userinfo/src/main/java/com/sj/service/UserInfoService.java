package com.sj.service;


import com.sj.dto.AddressDto;
import com.sj.dto.UserDTO;
import com.sj.dto.UserInfoDto;
import com.sj.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService {
    //检索姓名是否存在
    public ResultVO checkName(String username);
    //检索用户登录状态
    public Integer checkUserState(String username);
    //修改用户的登录状态
    public int updateUserState(String username,Integer state);
    //登录返回值
    public ResultVO returnUserVo(String username);
    //注册用户
    public ResultVO registerUser(UserDTO userDTO);
    //用户id查询信息
    public ResultVO findByUserId(Integer userId);
    //头像上传
    public ResultVO uploadIcon(MultipartFile file);
    //修改用户信息
    public ResultVO updateUserInfo(UserInfoDto userInfoDto);

    /*===============================用户地址操作=======================================*/
    //查询用户的地址列表
    public ResultVO findAllAddressList(Integer userId);
    //用户修改选中地址Id
    public ResultVO updateUserAddressId(Integer userId,Integer addressId);
    //添加新地址
    public ResultVO saveAddress(AddressDto addressDto);
    //addressId查询地址
    public ResultVO findByAddressId(Integer addressId);
    //修改地址
    public ResultVO updateAddress(AddressDto addressDto);
    //删除地址
    public ResultVO deleteAddress(Integer userId,Integer addressId,Integer a);
}
