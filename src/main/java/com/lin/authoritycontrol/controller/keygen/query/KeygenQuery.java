package com.lin.authoritycontrol.controller.keygen.query;

import com.lin.authoritycontrol.common.base.PageForm;
import lombok.Data;

/**
 * 秘钥查询
 *
 * @author 林维家
 * @since 2024/12/30 21:25
 */
@Data
public class KeygenQuery extends PageForm {

    /**
     * api标识
     */
    private String apiKey;

    /**
     * 名称
     */
    private String apiName;

    /**
     * 业务类型
     */
    private String relType;

    /**
     * 秘钥类型
     */
    private String keyType;

    /**
     * 启用标记
     */
    private Boolean enableFlag;
}
