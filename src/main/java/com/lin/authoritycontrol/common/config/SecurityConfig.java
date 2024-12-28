package com.lin.authoritycontrol.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security配置
 *
 * @author 林维家
 * @since 2024/12/28 下午12:59
 */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用 Argon2PasswordEncoder
        return new Argon2PasswordEncoder(
                16, // 内存消耗（KB）
                32, // 盐值长度（bytes）
                1,  // 并行度（线程数）
                3,  // 哈希迭代次数
                16  // 输出哈希长度（bytes）
        );
    }
}
