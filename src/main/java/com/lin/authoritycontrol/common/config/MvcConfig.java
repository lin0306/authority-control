package com.lin.authoritycontrol.common.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置
 *
 * @author 林维家
 * @since 2024-09-08 12:17 上午
 */
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加自定义静态资源路径
        registry.addResourceHandler("/static/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置CORS规则
        registry.addMapping("/*") // 指定路径匹配规则
                .allowedOrigins("*") // 允许所有源
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // 允许的方法
                .allowedHeaders("*") // 允许所有头
                .allowCredentials(true) // 是否允许发送凭据
                .maxAge(3600); // 预检请求的有效期
    }
}
