package org.example.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private long buyerId;
    private long sellerId;
    /**
     * 订单更新时间：用Timestamp类型有问题故改用String
     */
    private String time;
    private String note;
    /**
     * status：0表示待支付，1表示已支付
     */
    private int status;
}

