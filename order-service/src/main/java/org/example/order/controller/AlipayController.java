package org.example.order.controller;

import com.alipay.api.AlipayApiException;
import org.example.feign.pojo.User;
import org.example.feign.result.Result;
import org.example.feign.util.ResultUtil;
import org.example.order.pojo.Order;
import org.example.order.pojo.pay.AlipayBean;
import org.example.order.service.OrderService;
import org.example.order.service.PayService;
import org.example.feign.clients.UserClients;
import org.example.feign.clients.BookClients;
import org.example.feign.clients.CartClient;
import org.example.order.utils.OrderCode;
import org.example.order.view.OrderInfo;
import org.example.order.view.OrderItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/order")
public class AlipayController {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserClients userClients;
    @Autowired
    private BookClients bookClients;
    @Autowired
    private CartClient cartClient;

    /**
     * 沙箱支付
     * @param totalAmount 金额
     * @param subject 商品名称
     * @return 支付界面
     * @throws AlipayApiException 错误处理
     */
    @PostMapping(value = "/alipay")
    public String alipay(@RequestParam("totalAmount") String totalAmount,
                         @RequestParam("subject") String subject,
                         @RequestBody List<OrderInfo> orderInfos) throws AlipayApiException{
        /* 修改所有订单的备注信息 */
        for (OrderInfo orderInfo:orderInfos){
            String id = orderInfo.getId();
            String note = orderInfo.getNote();
            orderService.updateOrderNote(note,id);
        }
        /* 沙箱支付 */
        Double amount = Double.parseDouble(totalAmount);
        DecimalFormat format = new DecimalFormat("0.00");
        String a = format.format(amount);
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(OrderCode.getOrderCode());
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(a);
        alipayBean.setBody("哈易书平台上购买二手书");
        return payService.aliPay(alipayBean);
    }

    @PostMapping("/paySuccess")
    @ResponseBody
    public Result paySuccess(@RequestBody List<OrderInfo> orderInfos){
        for (OrderInfo orderInfo:orderInfos){
            String id = orderInfo.getId();
            /* 1.改变订单状态,更新时间和置status=1 */
            Order order = new Order();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(new Date());
            order.setId(id);order.setTime(time);order.setStatus(1);
            orderService.updateOrderStatus(order);
            /* 2.改变seller的money和sales */
            long sellerId = orderInfo.getSellerId();
            BigDecimal subTotal = orderInfo.getSubTotal();
            User user = userClients.getInfo(sellerId);
            subTotal = subTotal.add(user.getMoney());
            user.setMoney(subTotal);
            int sales = user.getSales() + orderInfo.getItemInfos().size();
            user.setSales(sales);
            userClients.setMoney(user);
            userClients.setSales(user);
            /* 3.遍历orderItemInfo，根据bid */
            for (OrderItemInfo info:orderInfo.getItemInfos()){
                int bid = info.getBid();
                /* 设置书本状态为已出售 */
                bookClients.sellBook(bid);
                /* 将购物车相关记录置为无效 */
                cartClient.deActiveCart(bid);
                /* 将初此订单项之外的该书本的订单项删除 */
                orderService.sellBook(bid,info.getId());
            }
        }
        return ResultUtil.buildSuccessResult("支付成功，请等待卖家与您联系后获取书本",null);
    }
}
