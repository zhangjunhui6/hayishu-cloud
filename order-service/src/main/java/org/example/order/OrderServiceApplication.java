package org.example.order;

import org.example.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zjh
 */
@MapperScan("org.example.order.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.feign.clients", defaultConfiguration = DefaultFeignConfiguration.class)
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
