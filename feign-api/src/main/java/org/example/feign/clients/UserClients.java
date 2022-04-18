package org.example.feign.clients;

import org.example.feign.pojo.User;
import org.example.feign.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * UserClients
 *
 * @author Zjh
 * @date 2021/12/6 22:37
 **/
@FeignClient("userservice")
public interface UserClients {

    @GetMapping("/user/getInfo")
    User getInfo(@RequestParam("id") long id);

    @GetMapping("/user/getMoney")
    BigDecimal getMoney(@RequestParam("id") long id);

    @PostMapping("/user/setMoney")
    Result setMoney(@RequestBody User user);

    @PostMapping("/user/setSales")
    Result setSales(@RequestBody User user);

    @PostMapping("/user/setFans")
    Result setFans(@RequestBody User user);

    @GetMapping("/user/getAllUser")
    List<User> getAllUser();

    @GetMapping("/user/getOtherUsers")
    List<User> getOtherUsers(@RequestParam("id") long id,
                             @RequestParam("orderCol") String orderCol);
}
