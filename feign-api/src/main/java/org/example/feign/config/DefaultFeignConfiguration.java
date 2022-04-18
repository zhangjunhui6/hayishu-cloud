package org.example.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;


/**
 * DefaultFeignConfiguration
 *
 * @author Zjh
 * @date 2021/12/6 20:06
 **/
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.BASIC;
    }
}
