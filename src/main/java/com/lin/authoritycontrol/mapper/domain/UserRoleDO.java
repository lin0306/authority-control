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
 * 用户角色
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_role")
public class UserRoleDO implements Serializable {
    /**
     * 唯一编码
     */
    @TableId(value = "rel_id", type = IdType.ASSIGN_UUID)
    private String relId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private String roleId;

    private static final long serialVersionUID = 1L;
}