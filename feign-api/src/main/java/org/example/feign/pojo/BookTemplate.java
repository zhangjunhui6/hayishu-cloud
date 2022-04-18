package org.example.feign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author liulu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTemplate {
    private long isbn;
    private String title;
    private String author;
    private String cover;
    private String press;
    private BigDecimal price;
    private String abs;
    private int cid;
}
