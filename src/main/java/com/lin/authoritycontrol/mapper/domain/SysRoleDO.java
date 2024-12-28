package com.lin.authoritycontrol.mapper.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRoleDO implements Serializable {
    /**
     * 唯一编码
     */
    @TableId(value = "role_id", type = IdType.ASSIGN_UUID)
    private String roleId;

    private static final long serialVersionUID = 1L;
}