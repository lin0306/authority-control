package com.lin.authoritycontrol.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 林维家
 * @since 2024/12/25 下午7:58
 */
@SpringBootTest
class IdUtilTest {

    @Resource
    private IdUtil idUtil;

    @Test
    void genId() {
        for (int i = 0; i < 10; i++) {
            System.out.println("id: " + idUtil.genId());
        }
    }
}