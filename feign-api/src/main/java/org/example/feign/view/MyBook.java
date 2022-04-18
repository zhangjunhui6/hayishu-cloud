package org.example.feign.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * MyBook  我的售书架界面需要的数据
 *
 * @author Zjh
 * @date 2020/5/28 0:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBook {
    /**
     * book id
     */
    private int id;
    private long isbn;
    private String title;
    private String author;
    private String cover;
    private String press;
    private String degree;
    private BigDecimal price;
    private BigDecimal sellPrice;
}
