package org.example.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.feign.pojo.User;
import org.example.feign.result.Result;
import org.example.feign.util.ResultUtil;
import org.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * UserCommonController
 * 存放被其它service调用的api
 * @author Zjh
 * @date 2021/12/7 22:09
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserCommonController {
    @Autowired
    private UserService userService;

    @GetMapping("/getInfo")
    public User getInfo(@RequestParam("id") long id){
        return userService.findById(id);
    }

    @GetMapping("/getMoney")
    public BigDecimal getMoney(@RequestParam("id") long id){
        if (null != userService.findById(id)){
            return userService.queryMoneyById(id);
        } else {
            return BigDecimal.valueOf(0);
        }
    }

    @PostMapping("/setMoney")
    @ResponseBody
    public Result setMoney(@RequestBody User user){
        int res = userService.updateMoney(user);
        if (res == 1) {
            return ResultUtil.buildSuccessResult("提现成功!",null);
        } else {
            return ResultUtil.buildFailResult("提现失败!", null);
        }
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/setSales")
    public Result setSales(@RequestBody User user) {
        int res = userService.updateSales(user);
        return res == 1
                ? ResultUtil.buildSuccessResult("修改销售量成功", null)
                : ResultUtil.buildFailResult("修改销售量失败", null);
    }

    @PostMapping("/setFans")
    public Result setFans(@RequestBody User user) {
        int res = userService.updateFans(user);
        return res == 1
                ? ResultUtil.buildSuccessResult("修改粉丝数成功", null)
                : ResultUtil.buildFailResult("修改粉丝数失败", null);
    }

    @GetMapping("/getOtherUsers")
    public List<User> getOtherUsers(@RequestParam("id") long id,
                                    @RequestParam("orderCol") String orderCol) {
        return userService.getOtherUsers(id, orderCol);
    }
}
