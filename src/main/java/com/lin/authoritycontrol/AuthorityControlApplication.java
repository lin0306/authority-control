package com.lin.authoritycontrol;

import com.lin.authoritycontrol.common.config.RedisSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class AuthorityControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorityControlApplication.class, args);
    }

}
