package com.lin.authoritycontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties
@EnableScheduling
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class AuthorityControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityControlApplication.class, args);
    }

}
