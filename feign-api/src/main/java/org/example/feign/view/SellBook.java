package org.example.feign.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * SellBooks
 *
 * @author Zjh
 * @date 2020/5/29 20:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellBook {
    private long id;
    private int bid;
    private String name;
    private String sex;
    private BigDecimal sellPrice;
    private String degree;
}
