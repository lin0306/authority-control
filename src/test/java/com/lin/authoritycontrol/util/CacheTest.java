package com.lin.authoritycontrol.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * 缓存测试
 *
 * @author 林维家
 * @since 2024/12/27 下午4:37
 */
@SpringBootTest
public class CacheTest {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "secondaryRedisTemplate")
    private RedisTemplate<String, Object> secondRedisTemplate;

    @Test
    void test() {
        redisTemplate.opsForValue().set("0", "0");
        secondRedisTemplate.opsForValue().set("1", "1");
    }
}
