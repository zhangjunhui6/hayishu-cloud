package org.example.club;

import org.example.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClubApplication
 *
 * @author Zjh
 * @date 2021/12/6 16:19
 **/
@MapperScan("org.example.club.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.feign.clients", defaultConfiguration = DefaultFeignConfiguration.class)
public class ClubApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClubApplication.class, args);
    }
}
