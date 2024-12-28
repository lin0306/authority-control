package com.lin.authoritycontrol.controller.sys.role.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色vo
 *
 * @author 林维家
 * @since 2024/12/28 下午5:25
 */
@Data
public class RoleVO implements Serializable {

    /**
     * 唯一编码
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 标签
     */
    private String tags;

    /**
     * 备注
     */
    private String memo;
}
