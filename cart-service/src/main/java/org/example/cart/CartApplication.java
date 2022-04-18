package org.example.cart;

import org.example.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * UserApplication
 *
 * @author pzx
 * @date 2021/12/8
 **/
@MapperScan("org.example.cart.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.feign.clients", defaultConfiguration = DefaultFeignConfiguration.class)
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
