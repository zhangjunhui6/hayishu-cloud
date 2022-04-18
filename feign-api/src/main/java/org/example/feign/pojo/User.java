package org.example.feign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * User
 *
 * @author Zjh
 * @date 2021/12/6 15:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String password;
    private String sex;
    /**
     * identity:身份，0表示管理员，1表示用户
     */
    private int identity;
    private String addr;
    private BigDecimal money;
    private int sales;
    private int fans;
    private String time;
}
