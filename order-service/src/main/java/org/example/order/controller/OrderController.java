package org.example.order.controller;

import org.example.feign.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.order.service.OrderService;
import org.example.feign.clients.BookClients;
import org.example.feign.util.ResultUtil;
import org.example.feign.result.Result;
import org.example.feign.view.CartTable;
import org.example.order.view.OrderInfo;
import org.example.order.view.OrderItemInfo;
import org.example.order.pojo.Order;
import org.example.order.pojo.OrderItem;
import org.example.feign.pojo.Book;
import org.example.order.utils.OrderCode;

/**
 * @author DELL
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BookClients bookClients;

    /**
     * 购物车下订单模块(新建订单)
     * @param cartTableList 选中的购物车表
     * @param buyerId   买家ID
     * @return 订单详情
     */
    @PostMapping("/create")
    @ResponseBody
    public Result createOrder(@RequestBody List<CartTable> cartTableList, @RequestParam("buyerId") long buyerId){
        /*对cartTableList按照uid进行分组；此uid为卖家的id*/
        Map<Long,List<CartTable>> cartTables = cartTableList.stream().collect(Collectors.groupingBy(CartTable::getUid));
        /*定义返回的数据*/
        List<OrderInfo> orderInfoList = new ArrayList<>();
        /*而后循环对每一分组进行处理*/
        for (Map.Entry<Long,List<CartTable>> entry : cartTables.entrySet()){
            long sellerId = entry.getKey();
            List<CartTable> list = entry.getValue();
            /*先新建一个订单*/
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(new Date());
            String oid = OrderCode.getOrderCode();
            Order order = new Order(oid,buyerId,sellerId,time,"",0);
            int res = orderService.insertOrder(order);
            if (res != 1){
                System.out.println("订单创建失败!");
            }
            OrderInfo orderInfo = new OrderInfo(oid,sellerId,"",time,"",0,new BigDecimal(0),null);
            String sellerName = "";
            BigDecimal subTotal = BigDecimal.valueOf(0);
            List<OrderItemInfo> orderItemInfoList = new ArrayList<>();

            for (CartTable table:list){
                /*遍历，建立订单项*/
                OrderItem orderItem = new OrderItem(1,oid,table.getBid(),0);
                int res2 = orderService.insertOrderItem(orderItem);
                if (res2 != 1){
                    System.out.println("订单项建立失败!");
                }
                int id = orderService.queryItemId(oid,table.getBid());
                OrderItemInfo orderItemInfo = new OrderItemInfo(id,table.getBid(),0,table.getTitle(),table.getSellPrice());
                orderItemInfoList.add(orderItemInfo);
                sellerName = table.getName();
                subTotal = subTotal.add(table.getSellPrice());
            }
            orderInfo.setSellerName(sellerName);
            orderInfo.setSubTotal(subTotal);
            orderInfo.setItemInfos(orderItemInfoList);
            orderInfoList.add(orderInfo);
        }
        return ResultUtil.buildSuccessResult("成功",orderInfoList);
    }

    /**
     * 获取买家的相关订单
     * @param statuses 订单状态
     * @param buyerId 买家ID
     * @return 所有相关的订单详情
     */
    @PostMapping("/getBuyerOrders")
    @ResponseBody
    public Result getBuyerOrders(@RequestBody List<Integer> statuses, @RequestParam("buyerId") long buyerId){
        List<OrderInfo> orderInfos = orderService.getBuyerOrSellerOrders(buyerId,statuses,true);
        return ResultUtil.buildSuccessResult("成功!",orderInfos);
    }

    /**
     * 获取卖家订单
     * @param statuses 订单状态
     * @param sellerId 卖家ID
     * @return 所有相关订单
     */
    @PostMapping("/getSellerOrders")
    @ResponseBody
    public Result getSellerOrders(@RequestBody List<Integer> statuses,@RequestParam("sellerId") long sellerId){
        List<OrderInfo> orderInfos = orderService.getBuyerOrSellerOrders(sellerId,statuses,false);
        return ResultUtil.buildSuccessResult("成功!",orderInfos);
    }

    /**
     * 删除某一订单项：若只有一项则连带删除订单
     * @param id 包括订单项id
     * @return 删除结果
     */
    @PostMapping("/deleteOrderItem")
    @ResponseBody
    public Result deleteOrderItem(@RequestParam("id") int id){
        String oid = orderService.findOrderItemById(id).getOid();
        int res = orderService.deleteOrderItem(id);
        if (res == 1){
            if (orderService.orderItemIsExist(oid)){
                return ResultUtil.buildSuccessResult("删除订单项成功!",null);
            } else {
                int rest = orderService.deleteOrder(oid);
                if (rest == 1){
                    return ResultUtil.buildSuccessResult("删除订单项成功，该订单已作废!",null);
                } else {
                    return ResultUtil.buildSuccessResult("删除订单项成功，但该订单删除失败,原因未知!",null);
                }
            }
        } else {
            return ResultUtil.buildFailResult("删除失败!",null);
        }
    }

    /**
     * 删除订单
     * @param id 订单编号
     * @return 删除结果
     */
    @PostMapping("/deleteOrder")
    @ResponseBody
    public Result deleteOrder(@RequestParam("id") String id){
        if (!orderService.orderItemIsExist(id)){
            return ResultUtil.buildFailResult("订单里面不存在订单项，错误！",null);
        } else {
            orderService.deleteOrderItems(id);
            int res = orderService.deleteOrder(id);
            if (res == 1){
                return ResultUtil.buildSuccessResult("删除订单成功",null);
            } else {
                return ResultUtil.buildFailResult("删除订单失败!",null);
            }
        }
    }
    /**
     * 修改订单状态信息
     * @param order_id 订单编号(id)
     * @param order_status 订单状态(status)
     * @return 修改结果
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public Result changeStatus(@RequestParam("id") String order_id,
                               @RequestParam("status") int order_status){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Order order = new Order();
        order.setTime(time);
        order.setId(order_id);
        order.setStatus(order_status);
        if (!orderService.orderIsExist(order_id)){
            return ResultUtil.buildFailResult("订单不存在，错误！",null);
        } else {
            int res = orderService.updateOrderStatus(order);
            if (res == 1){
                return ResultUtil.buildSuccessResult("成功",null);
            } else {
                return ResultUtil.buildFailResult("失败！",null);
            }
        }
    }

    @GetMapping("/getAll")
    public List<Order> getAll(){
        return orderService.getAll();
    }


    /**
     * 修改订单orderitem状态信息
     * @param uid book的seller
     * @param sellPrice 新售价
     * @param degree 重新设置新旧
     * @param item_id orderitem表中的id
     * @return 修改结果
     */
    @PostMapping("/resale")
    @ResponseBody
    public Result resale(@RequestParam("sellPrice") BigDecimal sellPrice,
                         @RequestParam("degree") String degree,
                         @RequestParam("uid") long uid,
                         @RequestParam("item_id") int item_id){
        OrderItem orderItem = orderService.findOrderItemById(item_id);
        int res = orderService.resale(orderItem.getId());
        if (res == 1){
            int bid = orderItem.getBid();
            Book book = bookClients.getBook(bid);
            book.setUid(uid);
            book.setSellPrice(sellPrice);
            book.setDegree(degree);
            book.setStatus(0);
            // 注意这个insertbook
            int rest = bookClients.insertBook(book).getCode();
            if (rest == ResultCode.SUCCESS.getCode()){
                return ResultUtil.buildSuccessResult("书本转售成功!",null);
            } else {
                return ResultUtil.buildFailResult("书本转售失败!",null);
            }
        } else {
            return ResultUtil.buildFailResult("书本转售失败!",null);
        }
    }

    @PostMapping("/deleteBook")
    public void deleteBook(@RequestParam("bid") int bid){
        orderService.deleteBook(bid);
    }
}
