package com.sj.service.impl;

import com.sj.Until.KeyUtil;
import com.sj.dto.AddressDto;
import com.sj.dto.UserDTO;
import com.sj.dto.UserInfoDto;
import com.sj.entity.AddressSave;
import com.sj.entity.UserAddress;
import com.sj.entity.UserInfo;
import com.sj.repository.UserAddressRepository;
import com.sj.repository.UserRepository;
import com.sj.result.ResultUtil;
import com.sj.service.UserInfoService;
import com.sj.vo.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAddressRepository addressRepository;

    private static  String salt =null;
    private static  String encodePassword=null;

    //检索姓名
    @Override
    public ResultVO checkName(String username) {
        UserInfo user = userRepository.findAllByUserName(username);
        if (user != null) {
            return ResultUtil.success("用户名已存在 !");
        }
        return ResultUtil.error("用户名不存在 !");
    }

    //检索用户登录状态
    @Override
    public Integer checkUserState(String username) {
        UserInfo user=userRepository.findAllByUserName(username);
        return user.getState();
    }
    //修改用户的登录状态
    @Override
    public int updateUserState(String username,Integer state) {
        int result=userRepository.updateUserState(username,state);
        if(result!=0){
            return 1;
        }
        return 0;
    }

    //登录传值
    @Override
    public ResultVO returnUserVo(String username) {
        UserInfo userInfo = userRepository.findAllByUserName(username);
        UserVO userVO = new UserVO();
        userVO.setUserId(userInfo.getUser_id());
        userVO.setUsername(userInfo.getUser_name());
        userVO.setPassword(userInfo.getUser_password());
        userVO.setUserAddressId(userInfo.getUser_addressid());
        userVO.setUserIcon(userInfo.getUser_icon());
        userVO.setUserPerm(userInfo.getUser_perm());
        return ResultUtil.success(userVO);
    }

    //注册
    @Override
    public ResultVO registerUser(UserDTO userDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name(userDTO.getUsername());
        userInfo.setUser_password(userDTO.getPassword());
        userInfo.setUser_role(userDTO.getRole());
        userInfo.setUser_perm(userDTO.getPerm());

        int result = 0;
        //注册
        if (userInfo.getUser_id() == null) {
            int times = 2;
            salt=new SecureRandomNumberGenerator().nextBytes().toString();
            userInfo.setUser_salt(salt);
            encodePassword = new SimpleHash("md5", userInfo.getUser_password(), salt, times).toString();
            userInfo.setUser_password(encodePassword);
            result = userRepository.registerUser(userInfo);
        }
        if (result == 1) {
            return ResultUtil.success(userDTO.getUsername() + "，注册成功！");
        } else {
            return ResultUtil.error("注册失败，请重新注册！");
        }
    }

    //用户id查询信息
    @Override
    public ResultVO findByUserId(Integer userId) {
        UserInfo userInfo=userRepository.findAllById(userId);
        UserInfoVO userInfoVO=new UserInfoVO();
        userInfoVO.setUsername(userInfo.getUser_name());
        userInfoVO.setPassword(userInfo.getUser_password());
        Map<String,String> iconMap=new HashMap<>();
        iconMap.put("url",userInfo.getUser_icon());
        List<Map<String,String>> iconList=new ArrayList<>();
        iconList.add(iconMap);
        userInfoVO.setUploader(iconList);

        return ResultUtil.success(userInfoVO);
    }

    //头像上传
    @Override
    public ResultVO uploadIcon(MultipartFile file) {
            String type=file.getContentType();
            if(type!=null){
                type=type.substring(type.indexOf("/")+1);
            }
            if("jpeg".equals(type)){
                type=".jpg";
            }
            String folder=System.getProperty("user.dir")+"_images";
            File imageFolder=new File(folder);
            String fileName="T_"+ KeyUtil.createId()+type;
            File f=new File(imageFolder,fileName);

            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }

        try {
            file.transferTo(f);
            String imgUrl="http://127.0.0.1:8040/img/file/"+f.getName();
            return ResultUtil.success(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error("上传失败");
        }

    }
    //修改用户信息
    @Override
    public ResultVO updateUserInfo(UserInfoDto userInfoDto) {
            UserInfo userInfo=null;
            /*没有修改密码的*/
        if(userInfoDto.getPassword()==null){
            userInfo=new UserInfo();
            userInfo.setUser_id(userInfoDto.getUserId());
            userInfo.setUser_name(userInfoDto.getUsername());
            userInfo.setUser_icon(userInfoDto.getImgUrl());

            int result=userRepository.updateUserInfo1(userInfo);

            if(result!=0){
                UserVO userVO = new UserVO();
                userVO.setUserId(userInfo.getUser_id());
                userVO.setUsername(userInfo.getUser_name());
                userVO.setUserIcon(userInfo.getUser_icon());
                return  ResultUtil.success(userVO);
            }
            return ResultUtil.error("保存失败");
        }/*全部修改的*/
        else{
            userInfo=new UserInfo();
            userInfo.setUser_id(userInfoDto.getUserId());
            userInfo.setUser_name(userInfoDto.getUsername());
            userInfo.setUser_icon(userInfoDto.getImgUrl());
            salt=new SecureRandomNumberGenerator().nextBytes().toString();
            userInfo.setUser_salt(salt);
            encodePassword=new SimpleHash("md5", userInfoDto.getPassword(), salt, 2).toString();
            userInfo.setUser_password(encodePassword);

            int result=userRepository.updateUserInfo2(userInfo);

            if(result!=0){
                UserVO userVO = new UserVO();
                userVO.setUserId(userInfo.getUser_id());
                userVO.setUsername(userInfo.getUser_name());
                userVO.setPassword(userInfo.getUser_password());
                userVO.setUserIcon(userInfo.getUser_icon());
                return  ResultUtil.success(userVO);
            }
            return ResultUtil.error("保存失败");
        }
    }

    /*=================================用户地址管理======================================*/
    //查询用户的地址列表
    @Override
    public ResultVO findAllAddressList(Integer userId) {
        List<UserAddress> userAddressList = addressRepository.findAllById(userId);
        String aa = "";
        List<AddressListVO> addressListVOList = userAddressList.stream()
                .map(e -> new AddressListVO(
                        e.getUser_addressid(),
                        e.getUser_phone(),
                        e.getUser_address().replaceAll("/", aa),
                        e.getAddress_name()
                )).collect(Collectors.toList());
        return ResultUtil.success(addressListVOList);
    }

    //选择地址id
    @Override
    public ResultVO updateUserAddressId(Integer userId, Integer addressId) {
        int result = userRepository.updateUserAddressId(userId, addressId);
        if (result == 1) {
            return ResultUtil.success("选择地址成功！");
        } else {
            return ResultUtil.error("选择地址失败！");
        }

    }

    //添加新地址
    @Override
    public ResultVO saveAddress(AddressDto addressDto) {
        StringBuffer user_address = new StringBuffer();
        user_address.append(addressDto.getProvince() + "/");
        user_address.append(addressDto.getCity() + "/");
        user_address.append(addressDto.getCounty() + "/");
        user_address.append(addressDto.getAddressDetail());

        AddressSave addressSave = new AddressSave();
        addressSave.setUser_id(addressDto.getUserId());
        addressSave.setAddress_name(addressDto.getName());
        addressSave.setUser_phone(addressDto.getTel());
        addressSave.setUser_address(user_address.toString());
        addressSave.setUser_area(addressDto.getAreaCode());

        int result = addressRepository.saveAddress(addressSave);

        if (result == 1) {
            return ResultUtil.success(addressSave.getUser_addressid());
        }

        return ResultUtil.error("添加地址失败");
    }

    @Override
    public ResultVO findByAddressId(Integer addressId) {
        UserAddress userAddress = addressRepository.findByAddressId(addressId);
        AddressEditVO addressEditVO = new AddressEditVO();
        addressEditVO.setId(userAddress.getUser_addressid());
        addressEditVO.setUserId(userAddress.getUserInfo().getUser_id());
        addressEditVO.setName(userAddress.getAddress_name());
        addressEditVO.setTel(userAddress.getUser_phone());
        addressEditVO.setAreaCode(userAddress.getUser_area().toString());

        String[] addressDetails = userAddress.getUser_address().split("/");
        addressEditVO.setProvince(addressDetails[0]);
        addressEditVO.setCity(addressDetails[1]);
        addressEditVO.setCounty(addressDetails[2]);
        addressEditVO.setAddressDetail(addressDetails[3]);

        return ResultUtil.success(addressEditVO);
    }

    @Override
    public ResultVO updateAddress(AddressDto addressDto) {
        StringBuffer user_address = new StringBuffer();
        user_address.append(addressDto.getProvince() + "/");
        user_address.append(addressDto.getCity() + "/");
        user_address.append(addressDto.getCounty() + "/");
        user_address.append(addressDto.getAddressDetail());

        AddressSave addressSave = new AddressSave();
        addressSave.setUser_addressid(addressDto.getId());
        addressSave.setAddress_name(addressDto.getName());
        addressSave.setUser_phone(addressDto.getTel());
        addressSave.setUser_address(user_address.toString());
        addressSave.setUser_area(addressDto.getAreaCode());

        int result=addressRepository.updateAddress(addressSave);

        if(result!=0){
            return  ResultUtil.success("修改成功");
        }
        return ResultUtil.error("修改失败");
    }

    @Override
    public ResultVO deleteAddress(Integer userId, Integer addressId, Integer a) {
        int result;
        if(a==0){ result=addressRepository.deleteAddress(addressId);
           if(result!=0){
               userRepository.updateUserAddressId(userId,0);
               return ResultUtil.success("删除成功！");
           }
        }else{
            result=addressRepository.deleteAddress(addressId);
            if (result!=0){
                return ResultUtil.success("删除成功！");
            }
        }
        return ResultUtil.error("删除失败！");
    }
}
