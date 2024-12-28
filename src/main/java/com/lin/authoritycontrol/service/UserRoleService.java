package com.lin.authoritycontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.authoritycontrol.controller.sys.role.vo.RoleVO;
import com.lin.authoritycontrol.mapper.domain.UserRole;

import java.util.List;

/**
 * 用户角色service
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 保存用户角色
     */
    void saveUserRole(String userId, List<String> roleIds);

    /**
     * 更新用户角色
     */
    void updateUserRole(String userId, List<String> roleIds);

    /**
     * 查询用户角色
     */
    List<RoleVO> getUserRoles(String userId);
}
