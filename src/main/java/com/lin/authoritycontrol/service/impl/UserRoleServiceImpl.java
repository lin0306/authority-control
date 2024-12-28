package com.lin.authoritycontrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.controller.sys.role.vo.RoleVO;
import com.lin.authoritycontrol.mapper.UserRoleMapper;
import com.lin.authoritycontrol.mapper.domain.UserRole;
import com.lin.authoritycontrol.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色service实现
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRole(String userId, List<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }
        List<UserRole> userRoles = roleIds.stream().map(roleId -> {
            UserRole userRole = new UserRole(userId, roleId);
            userRole.setCreatorId(""); // 这里暂时先置空
            userRole.setCreateTime(LocalDateTime.now());
            return userRole;
        }).collect(Collectors.toList());
        saveBatch(userRoles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRole(String userId, List<String> roleIds) {
        List<UserRole> list = lambdaQuery().eq(UserRole::getUserId, userId).list();
        if (CollectionUtils.isEmpty(roleIds) && !CollectionUtils.isEmpty(list)) {
            removeBatchByIds(list.stream().map(UserRole::getRelId).collect(Collectors.toList()));
            return;
        }
        if (!CollectionUtils.isEmpty(list)) {
            if (list.size() == roleIds.size()) {
                // 角色数量一致，且角色一致，则不更新
                if (list.stream().allMatch(userRole -> roleIds.contains(userRole.getRoleId()))) {
                    return;
                }
            }
        }
        remove(lambdaQuery().eq(UserRole::getUserId, userId));
        saveUserRole(userId, roleIds);
    }

    @Override
    public List<RoleVO> getUserRoles(String userId) {
        return baseMapper.getUserRoles(userId);
    }
}
