package com.lin.authoritycontrol.controller.keygen.vo;

import com.lin.authoritycontrol.mapper.domain.KeygenConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 秘钥vo
 *
 * @author 林维家
 * @since 2024/12/30 21:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeygenVO implements Serializable {

    /**
     * 唯一标识
     */
    private String apiId;

    /**
     * api标识
     */
    private String apiKey;

    /**
     * 名称
     */
    private String apiName;

    /**
     * 描述
     */
    private String describe;

    /**
     * 业务类型
     */
    private String relType;

    /**
     * 秘钥类型
     */
    private String keyType;

    /**
     * 标签
     */
    private String tags;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 更新频率
     */
    private String updateFrequency;

    /**
     * 有效期开始时间
     */
    private LocalDateTime startTime;

    /**
     * 有效期结束时间
     */
    private LocalDateTime endTime;

    /**
     * 启用标记
     */
    private Boolean enableFlag;

    public KeygenVO(KeygenConfig config) {
        this.apiId = config.getApiId();
        this.apiKey = config.getApiKey();
        this.apiName = config.getApiName();
        this.describe = config.getDescribe();
        this.relType = config.getRelType();
        this.keyType = config.getKeyType();
        this.tags = config.getTags();
        this.privateKey = config.getPrivateKey();
        this.publicKey = config.getPublicKey();
        this.updateFrequency = config.getUpdateFrequency();
        this.startTime = config.getStartTime();
        this.endTime = config.getEndTime();
        this.enableFlag = config.getEnableFlag();
    }
}
