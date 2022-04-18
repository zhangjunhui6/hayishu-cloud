package org.example.book;

import org.example.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liulu
 */
@MapperScan("org.example.book.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.feign.clients", defaultConfiguration = DefaultFeignConfiguration.class)
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
