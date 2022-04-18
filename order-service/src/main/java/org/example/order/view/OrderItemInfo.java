package org.example.order.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemInfo {
    /**
     * id：订单项的ID
     */
    private int id;
    /**
     * bid：书本id
     */
    private int bid;
    /**
     * 是否转售
     */
    private int isResale;
    /**
     * title：书本名称
     */
    private String title;
    /**
     * sellPrice：售价
     */
    private BigDecimal sellPrice;
}
