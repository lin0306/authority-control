package com.lin.authoritycontrol.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.common.enums.log.LogRelTypeEnum;
import com.lin.authoritycontrol.common.enums.log.LogTypeEnum;
import com.lin.authoritycontrol.controller.sys.role.vo.RoleVO;
import com.lin.authoritycontrol.controller.sys.user.vo.UserRoleVO;
import com.lin.authoritycontrol.mapper.SysRoleMapper;
import com.lin.authoritycontrol.mapper.UserRoleMapper;
import com.lin.authoritycontrol.mapper.domain.UserRole;
import com.lin.authoritycontrol.service.SysLogService;
import com.lin.authoritycontrol.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户角色service实现
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final SysLogService sysLogService;
    private final SysRoleMapper sysRoleMapper;

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
//    @Transactional(rollbackFor = Exception.class)
    public void updateUserRole(String userId, List<String> roleIds) {
        List<UserRoleVO> list = baseMapper.getUserRoleList(userId);
        if (CollectionUtils.isEmpty(roleIds) && !CollectionUtils.isEmpty(list)) {
            removeBatchByIds(list.stream().map(UserRoleVO::getRelId).collect(Collectors.toList()));
            sysLogService.addLog(LogTypeEnum.USER, userId, LogRelTypeEnum.ROLE_DELETE, "删除【" + list.stream().map(UserRoleVO::getRoleName).collect(Collectors.joining("，")) + "】角色");
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
        removeBatchByIds(list.stream().map(UserRoleVO::getRelId).collect(Collectors.toList()));
        saveUserRole(userId, roleIds);
        // 筛选出被删除的角色，并记录日志
        if (!CollectionUtils.isEmpty(list)) {
            List<UserRoleVO> deleteRoleIds = list.stream().filter(role -> !roleIds.contains(role.getRoleId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(deleteRoleIds)) {
                sysLogService.addLog(LogTypeEnum.USER, userId, LogRelTypeEnum.ROLE_DELETE, "删除【" + deleteRoleIds.stream().map(UserRoleVO::getRoleName).collect(Collectors.joining("，")) + "】角色");
            }
        }
        // 筛选出新增的角色，并记录日志
        if (!CollectionUtils.isEmpty(roleIds)) {
            String addRoles = roleIds.stream()
                    .filter(roleId -> {
                        if (CollectionUtils.isEmpty(list)) {
                            return true;
                        } else {
                            return list.stream().noneMatch(role -> Objects.equals(role.getRoleId(), roleId));
                        }
                    })
                    .map(roleId -> sysRoleMapper.selectById(roleId).getRoleName()).collect(Collectors.joining("，"));
            if (StrUtil.isNotBlank(addRoles)) {
                sysLogService.addLog(LogTypeEnum.USER, userId, LogRelTypeEnum.ROLE_ADD, "添加【" + addRoles + "】角色");
            }
        }
    }

    @Override
    public List<RoleVO> getUserRoles(String userId) {
        return baseMapper.getUserRoles(userId);
    }
}
