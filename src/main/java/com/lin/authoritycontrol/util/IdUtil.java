package com.lin.authoritycontrol.util;

import cn.hutool.core.lang.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * id工具类
 *
 * @author 林维家
 * @since 2024/12/25 下午4:40
 */
@Repository
@Slf4j
public class IdUtil {

    private static Snowflake snowflake = null;

    @Value("${snowflake.workerId:1}")
    private Long workerId;
    @Value("${snowflake.datacenterId:1}")
    private Long datacenterId;

    @PostConstruct
    public void init() {
        log.info("雪花算法初始化：{ workerId: {}, datacenterId: {} }", workerId, datacenterId);
        snowflake = cn.hutool.core.util.IdUtil.getSnowflake(workerId, datacenterId);
    }

    public String genId() {
        return String.valueOf(snowflake.nextId());
    }
}
