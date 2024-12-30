package com.lin.authoritycontrol.controller.keygen.form;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 秘钥表单
 *
 * @author 林维家
 * @since 2024/12/30 21:14
 */
@Data
public class KeygenUpdateForm implements Serializable {

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
     * 标签
     */
    private String tags;

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
}
