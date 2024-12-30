package com.lin.authoritycontrol.mapper.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 秘钥配置
 *
 * @author 林维家
 * @since 2024/12/30 17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "keygen_config")
public class KeygenConfig implements Serializable {
    /**
     * 唯一标识
     */
    @TableId(value = "api_id", type = IdType.ASSIGN_UUID)
    private String apiId;

    /**
     * api标识
     */
    @TableField(value = "api_key")
    private String apiKey;

    /**
     * 名称
     */
    @TableField(value = "api_name")
    private String apiName;

    /**
     * 描述
     */
    @TableField(value = "`describe`")
    private String describe;

    /**
     * 秘钥类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 标签
     */
    @TableField(value = "tags")
    private String tags;

    /**
     * 私钥
     */
    @TableField(value = "private_key")
    private String privateKey;

    /**
     * 公钥
     */
    @TableField(value = "public_key")
    private String publicKey;

    /**
     * 更新频率
     */
    @TableField(value = "update_frequency")
    private String updateFrequency;

    /**
     * 有效期开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 有效期结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 启用标记
     */
    @TableField(value = "enable_flag")
    private Boolean enableFlag;

    /**
     * 创建人id
     */
    @TableField(value = "creator_id")
    private String creatorId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改人id
     */
    @TableField(value = "modifier_id")
    private String modifierId;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time")
    private LocalDateTime modifyTime;

    /**
     * 删除标记
     */
    @TableField(value = "delete_flag")
    private Boolean deleteFlag;

    private static final long serialVersionUID = 1L;
}