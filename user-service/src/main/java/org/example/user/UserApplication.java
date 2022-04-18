package org.example.user;

import org.example.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * UserApplication
 *
 * @author Zjh
 * @date 2021/12/6 15:53
 **/
@MapperScan("org.example.user.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.feign.clients", defaultConfiguration = DefaultFeignConfiguration.class)
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
