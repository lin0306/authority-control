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
 * 登录日志
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "login_log")
public class LoginLog implements Serializable {
    /**
     * 唯一编码
     */
    @TableId(value = "login_id", type = IdType.ASSIGN_UUID)
    private Long loginId;

    /**
     * 登录用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 登录ip地址
     */
    @TableField(value = "`host`")
    private String host;

    /**
     * 登录地点
     */
    @TableField(value = "address")
    private String address;

    /**
     * 用户代理
     */
    @TableField(value = "user_agent")
    private String userAgent;

    /**
     * 系统类型
     */
    @TableField(value = "system_type")
    private String systemType;

    /**
     * 登录方式
     */
    @TableField(value = "device_type")
    private String deviceType;

    /**
     * 浏览器类型
     */
    @TableField(value = "browser_type")
    private String browserType;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    private LocalDateTime loginTime;

    private static final long serialVersionUID = 1L;
}