package org.example.order.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    /**
     * id：订单编号
     */
    private String id;
    /**
     * sellerId：卖(买)家的ID
     */
    private long sellerId;
    /**
     * sellerName：卖(买)家昵称
     */
    private String sellerName;
    /**
     * 订单更新时间
     */
    private String time;
    /**
     * note：备注信息
     */
    private String note;
    /**
     * status: 订单状态
     */
    private int status;
    /**
     * subTotal：小计
     */
    private BigDecimal subTotal;
    /**
     * itemInfos：订单项详情
     */
    private List<OrderItemInfo> itemInfos;
}
