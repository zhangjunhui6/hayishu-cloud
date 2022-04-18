package org.example.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.feign.pojo.User;
import org.example.feign.result.Result;
import org.example.feign.util.ResultUtil;
import org.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * UserController
 *
 * @author Zjh
 * @date 2021/12/6 15:52
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        user.setIdentity(1);
        user.setMoney(new BigDecimal(0));
        user.setSales(0);
        user.setFans(0);
        int res = userService.register(user);
        if (-2 == res){
            return ResultUtil.buildFailResult("该手机号已经注册过!",null);
        } else if (res == -1){
            return ResultUtil.buildFailResult("该昵称已被使用！",null);
        } else if (res == 0){
            return ResultUtil.buildFailResult("注册失败!",null);
        } else {
            return ResultUtil.buildSuccessResult("注册成功,确定进入登录界面",null);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user){
        User user1 = userService.login(user);
        if (null != user1){
            return ResultUtil.buildSuccessResult("登录成功",user1);
        } else {
            return ResultUtil.buildFailResult("手机号或密码错误!",null);
        }
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public Result changePassword(@RequestBody User user){
        int res = userService.changePassword(user);
        if (res == 1){
            return ResultUtil.buildSuccessResult("修改密码成功!请重新登录",null);
        } else {
            return ResultUtil.buildFailResult("修改密码失败!",null);
        }
    }

    @PostMapping("/setInfo")
    @ResponseBody
    public Result setInfo(@RequestBody User user){
        int res = userService.setInfo(user);
        if (res == 1){
            return ResultUtil.buildSuccessResult("修改成功!",null);
        } else {
            return ResultUtil.buildFailResult("修改失败!",null);
        }
    }
}
