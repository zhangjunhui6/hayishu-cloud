package org.example.club.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.club.pojo.Follow;
import org.example.club.service.FollowService;
import org.example.feign.pojo.User;
import org.example.feign.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FollowController
 *
 * @author Zjh
 * @date 2021/12/6 16:25
 **/
@Slf4j
@RestController
@RequestMapping("/club")
public class FollowController {
    @Autowired
    private FollowService followService;

    @GetMapping("/getOtherUsers")
    public List<User> getOtherUsers(@RequestParam("id") long id,
                                    @RequestParam("index") int index) {
        return followService.getOtherUsers(id, index);
    }

    @GetMapping("/queryOtherUsers")
    public List<User> queryOtherUsers(@RequestParam("id") long id,
                                      @RequestParam("index") int index,
                                      @RequestParam("keyword") String keyword) {
        return followService.queryOtherUsers(id, index, keyword);
    }

    @PostMapping("/changeFollowStatus")
    @ResponseBody
    public Result changeFollowStatus(@RequestBody Follow follow) {
        return followService.changeFollowStatus(follow);
    }

    @GetMapping("/getStatus")
    public int getStatus(@RequestParam("uid") long uid,
                         @RequestParam("followId") long followId) {
        return followService.getStatus(uid, followId);
    }
}
