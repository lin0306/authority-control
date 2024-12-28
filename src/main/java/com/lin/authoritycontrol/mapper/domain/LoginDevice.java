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
 * 登录设备
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "login_device")
public class LoginDevice implements Serializable {
    /**
     * 设备id
     */
    @TableId(value = "device_id", type = IdType.ASSIGN_UUID)
    private Long deviceId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * IP地址
     */
    @TableField(value = "`host`")
    private String host;

    /**
     * 位置
     */
    @TableField(value = "address")
    private String address;

    /**
     * 最后一次登录时间
     */
    @TableField(value = "last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}