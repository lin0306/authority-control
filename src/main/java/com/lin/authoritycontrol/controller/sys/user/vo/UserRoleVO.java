package com.lin.authoritycontrol.controller.sys.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色vo
 *
 * @author 林维家
 * @since 2024/12/28 下午7:07
 */
@Data
public class UserRoleVO implements Serializable {

    /**
     * 用户角色关联id
     */
    private String relId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;
}
