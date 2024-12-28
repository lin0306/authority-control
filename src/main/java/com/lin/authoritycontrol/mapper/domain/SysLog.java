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
import java.util.Date;

/**
 * 系统日志
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_log")
public class SysLog implements Serializable {
    /**
     * 唯一编码
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * 日志类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 关联的业务id
     */
    @TableField(value = "rel_id")
    private String relId;

    /**
     * 关联的业务的类型
     */
    @TableField(value = "rel_type")
    private String relType;

    /**
     * 日志内容
     */
    @TableField(value = "`describe`")
    private String describe;

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

    private static final long serialVersionUID = 1L;
}