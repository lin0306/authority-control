package com.lin.authoritycontrol.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

/**
 * 系统配置
 *
 * @author 林维家
 * @since 2024/12/28 下午2:33
 */
@Data
@Configuration
public class SysParam {

    @Value("${aes.keyPath}")
    private String aesFilePath;

}
