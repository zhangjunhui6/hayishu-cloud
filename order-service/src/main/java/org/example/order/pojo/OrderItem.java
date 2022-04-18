package org.example.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private int id;
    /**
     * 订单编号
     */
    private String oid;
    /**
     * 商品编号
     */
    private int bid;
    /**
     * 是否转售
     */
    private int isResale;
}

