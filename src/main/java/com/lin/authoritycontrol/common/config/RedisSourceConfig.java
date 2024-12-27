package com.lin.authoritycontrol.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis数据源配置
 *
 * @author 林维家
 * @since 2024/12/27 下午3:25
 */
@Configuration
public class RedisSourceConfig {

    // ============================= primary start ================================
    @Bean("primaryRedisConfig")
    @ConfigurationProperties(prefix = "spring.redis.primary")
    @Primary
    public RedisStandaloneConfiguration primaryRedisConfig() {
        return new RedisStandaloneConfiguration();
    }

    @Bean("primaryLettuceConnectionFactory")
    @Primary
    public LettuceConnectionFactory primaryLettuceConnectionFactory(@Qualifier("primaryRedisConfig") RedisStandaloneConfiguration primaryRedisConfig) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(primaryRedisConfig);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean("redisTemplate")
    @Primary
    public RedisTemplate<String, Object> primaryRedisTemplate(@Qualifier("primaryLettuceConnectionFactory") LettuceConnectionFactory primaryLettuceConnectionFactory) {
        return createRedisTemplate(primaryLettuceConnectionFactory);
    }

    // ============================= primary end ================================

    // ============================= secondary start ================================


    @Bean("secondaryLettuceConnectionFactory")
    public LettuceConnectionFactory secondaryLettuceConnectionFactory(@Qualifier("secondaryRedisConfig") RedisStandaloneConfiguration secondaryRedisConfig) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(secondaryRedisConfig);
        factory.afterPropertiesSet();
        return factory;
    }


    @Bean(name = "secondaryRedisTemplate")
    public RedisTemplate<String, Object> secondaryRedisTemplate(@Qualifier("secondaryLettuceConnectionFactory") LettuceConnectionFactory secondaryLettuceConnectionFactory) {
        return createRedisTemplate(secondaryLettuceConnectionFactory);
    }

    private RedisTemplate<String, Object> createRedisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    // ============================= secondary end ================================

    @Bean("secondaryRedisConfig")
    @ConfigurationProperties(prefix = "spring.redis.secondary")
    public RedisStandaloneConfiguration secondaryRedisConfig() {
        return new RedisStandaloneConfiguration();
    }

}
