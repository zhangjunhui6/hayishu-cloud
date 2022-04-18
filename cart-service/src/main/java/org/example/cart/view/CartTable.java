package org.example.cart.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zjh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartTable {
    /*根据uid和status从cart表获取id和bid*/
    /**
     *
     * id：购物车记录的id
     */
    private int id;
    /**
     * bid：书本项的id
     */
    private int bid;

    /*根据bid从book表获取uid,isbn,sellPrice和degree*/
    /**
     * uid：卖家的id
     */
    private long uid;
    /**
     * isbn：书本的isbn
     */
    private long isbn;
    /**
     * sellPrice：书本售价
     */
    private BigDecimal sellPrice;
    /**
     * degree：书本新旧程度
     */
    private String degree;

    /*根据uid从user表获取name*/
    /**
     * name：卖家昵称
     */
    private String name;

    /*根据isbn从booktemplate表获取title,author,press,price*/
    /**
     * title：书本名
     */
    private String title;
    /**
     * author：作者
     */
    private String author;
    /**
     * press：出版社
     */
    private String press;
    /**
     * price：原价
     */
    private BigDecimal price;
}
