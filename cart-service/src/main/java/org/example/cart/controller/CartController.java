package org.example.cart.controller;

import org.example.cart.service.CartService;
import org.example.cart.pojo.Cart;
import org.example.cart.view.CartTable;
import org.example.feign.result.Result;
import org.example.feign.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * CartController
 *
 * @author pzx
 * @date 2021/12/8
 **/
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/joinCart")
    @ResponseBody
    public Result joinCart(@RequestBody Cart cart){
//        System.out.println(cart);
        Cart cart1 = cartService.findByUidAndBid(cart);   // 先检查一下当前插入的数据是否冲突
        if (null == cart1){    // 空的，说明要插入的购物车记录在数据库中不存在
//            System.out.println(cart);
            int res = cartService.insertCart(cart);
            if (res == 1){
                return ResultUtil.buildSuccessResult("加入购物车成功，请前往购物车中查看",null);
            } else {
                return ResultUtil.buildFailResult("加入购物车失败!",null);
            }
        } else {
            if (cart.getStatus() == cart1.getStatus()){
                return ResultUtil.buildFailResult("购物车中已有该书本，请勿重复加入",null);
            } else {
                cart1.setStatus(cart.getStatus());
                int res = cartService.updateCart(cart1);
                if (res == 1){
                    return ResultUtil.buildSuccessResult("加入购物车成功",null);
                } else {
                    return ResultUtil.buildFailResult("加入购物车失败!",null);
                }

            }
        }
    }

    @GetMapping("/getMyCart")
    public List<CartTable> getMyCart(@RequestParam("uid") long uid, @RequestParam("status") int status){
        return cartService.getMyCart(uid, status);
    }

    @PostMapping("/deActiveCart")
    public Result deActiveCart(@RequestParam("bid") int bid){
        int res = cartService.deActiveCart(bid);
        if (res == 1){
            return ResultUtil.buildSuccessResult("更新购物车书籍状态成功",null);
        } else {
            return ResultUtil.buildFailResult("更新购物车书籍状态成功!",null);
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ArrayList<Integer> ids){
        if (ids.size() > 0){
            int res = 0;
            for (int id:ids){
                res += cartService.deleteById(id);
            }
            if (res == ids.size()){
                return ResultUtil.buildSuccessResult("删除成功!",null);
            } else {
                return ResultUtil.buildFailResult("删除失败!",null);
            }
        } else {
            return ResultUtil.buildFailResult("删除失败!",null);
        }
    }
}
