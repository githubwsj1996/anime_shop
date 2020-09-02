package com.sj.controller;

import com.sj.dto.UserDTO;
import com.sj.entity.UserInfo;
import com.sj.result.ResultUtil;
import com.sj.service.UserInfoService;
import com.sj.vo.ResultVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class LoginHandler {

    @Autowired
    private  UserInfoService userInfoService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody UserInfo user){
        Integer state=userInfoService.checkUserState(user.getUser_name());
        if(state==1){
            return ResultUtil.error("用户已登录！！！！");
        }else{
            userInfoService.updateUserState(user.getUser_name(),1);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUser_name(), user.getUser_password());
            try {
                subject.login(usernamePasswordToken);
                return userInfoService.returnUserVo(user.getUser_name());
            } catch (AuthenticationException e) {
                String message = "账号密码错误";
                return ResultUtil.error(message);
            }
        }
    }

    @PutMapping("/loginOut/{username}")
    public ResultVO  loginOut(@PathVariable("username") String username){
        int result=userInfoService.updateUserState(username,0);
        if(result!=0){
            return ResultUtil.success("小主,再见！！！！");
        }
        return  ResultUtil.error("注销失败！");
    }

    @GetMapping("/checkName/{username}")
    public ResultVO checkName(@PathVariable("username") String username){
        return userInfoService.checkName(username);
    }

    @PostMapping("/registerUser")
    public ResultVO registerUser(@RequestBody UserDTO userDTO){
      return userInfoService.registerUser(userDTO);
    }
}
