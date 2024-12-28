package com.lin.authoritycontrol.mapper.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUserDO implements Serializable {
    /**
     * 唯一编码
     */
    @TableId(value = "u_id", type = IdType.ASSIGN_UUID)
    private String uId;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户名
     */
    @TableField(value = "user_name_hash")
    private String userNameHash;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    private static final long serialVersionUID = 1L;
}