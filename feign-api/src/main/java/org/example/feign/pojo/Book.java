package org.example.feign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Book
 *
 * @author Zjh
 * @date 2020/5/24 23:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private long uid;
    private long isbn;
    private BigDecimal sellPrice;
    private String degree;
    /**
     * 状态码：-1:下架 0：待售 1：已出售
     */
    private int status;
}
