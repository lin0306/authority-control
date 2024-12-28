package com.lin.authoritycontrol.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.common.enums.log.LogRelTypeEnum;
import com.lin.authoritycontrol.common.enums.log.LogTypeEnum;
import com.lin.authoritycontrol.common.exception.CustomException;
import com.lin.authoritycontrol.controller.sys.role.vo.RoleVO;
import com.lin.authoritycontrol.controller.sys.user.form.UserSaveForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserUpdateForm;
import com.lin.authoritycontrol.controller.sys.user.query.UserQuery;
import com.lin.authoritycontrol.controller.sys.user.vo.UserVO;
import com.lin.authoritycontrol.mapper.SysUserMapper;
import com.lin.authoritycontrol.mapper.domain.SysUser;
import com.lin.authoritycontrol.mapper.domain.UserProfile;
import com.lin.authoritycontrol.service.SysLogService;
import com.lin.authoritycontrol.service.SysUserService;
import com.lin.authoritycontrol.service.UserProfileService;
import com.lin.authoritycontrol.service.UserRoleService;
import com.lin.authoritycontrol.util.AESUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 用户service实现
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final UserProfileService userProfileService;
    private final SysLogService sysLogService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserSaveForm user) {
        if (checkUserName(user.getUserName())) {
            throw new CustomException("用户名已存在，请重新填写");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUserName(AESUtil.encrypt(user.getUserName()));
        sysUser.setUserNameHash(AESUtil.hash(user.getUserName()));
        sysUser.setPassword(passwordEncoder.encode(user.getPassword()));
        sysUser.setCreatorId(""); // todo 暂时置空
        sysUser.setCreateTime(LocalDateTime.now());
        save(sysUser);

        // 保存用户角色
        userRoleService.saveUserRole(sysUser.getUId(), user.getRoleIds());
        // 保存用户个人信息
        userProfileService.saveProfile(sysUser.getUId(), user);

        sysLogService.addLog(LogTypeEnum.USER, sysUser.getUId(), LogRelTypeEnum.USER_CREATE, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserUpdateForm user) {
        // 保存用户角色
        userRoleService.updateUserRole(user.getUserId(), user.getRoleIds());
        // 保存用户个人信息
        userProfileService.updateProfile(user);
    }

    @Override
    public void updatePassword(String userId, String password) {
        SysUser sysUser = getHasPasswordById(userId);
        if (sysUser == null) {
            throw new CustomException("没有查询到用户");
        }
        String newPwd = passwordEncoder.encode(password);
        if (!Objects.equals(newPwd, sysUser.getPassword())) {
            sysUser.setPassword(newPwd);
            sysUser.setModifierId(""); // todo 暂时置空
            sysUser.setModifyTime(LocalDateTime.now());
            updateById(sysUser);
            sysLogService.addLog(LogTypeEnum.USER, userId, LogRelTypeEnum.PWD_UPDATE, null);
            // todo 需要下线所有在线设备
        }
    }

    @Override
    public void deleteUser(String userId) {
        SysUser user = getHasPasswordById(userId);
        if (user == null) {
            throw new CustomException("没有查询到用户");
        }
        user.setDeleteFlag(true);
        user.setModifierId(""); // todo 暂时置空
        user.setModifyTime(LocalDateTime.now());
        updateById(user);
        sysLogService.addLog(LogTypeEnum.USER, userId, LogRelTypeEnum.USER_DELETE, null);
    }

    @Override
    public PageVO<UserVO> queryUserPage(UserQuery query) {
        if (StrUtil.isNotBlank(query.getUserName())) {
            query.setUserName(AESUtil.hash(query.getUserName()));
        }
        if (StrUtil.isNotBlank(query.getIdCard())) {
            query.setIdCard(AESUtil.hash(query.getIdCard()));
        }
        Page<UserVO> iPage = baseMapper.queryUserPage(new Page<>(query.getCurrent(), query.getSize()), query);
        if (!CollectionUtils.isEmpty(iPage.getRecords())) {
            iPage.getRecords().forEach(user -> {
                user.setUserName(AESUtil.decrypt(user.getUserName()));
                user.setIdCard(AESUtil.decrypt(user.getIdCard()));
            });
        }
        return new PageVO<>(iPage);
    }

    @Override
    public UserVO getUser(String userId) {
        SysUser sysUser = getById(userId);
        if (sysUser == null) {
            return null;
        }
        UserProfile profile = userProfileService.getById(userId);
        List<RoleVO> roleList = userRoleService.getUserRoles(userId);
        return new UserVO(sysUser, profile, roleList);
    }

    @Override
    public SysUser getById(Serializable id) {
        return lambdaQuery()
                .eq(SysUser::getUId, id)
                .eq(SysUser::getDeleteFlag, false)
                .select(
                        SysUser::getUId,
                        SysUser::getUserName,
                        SysUser::getUserNameHash,
                        SysUser::getCreatorId,
                        SysUser::getCreateTime,
                        SysUser::getModifierId,
                        SysUser::getModifyTime
                )
                .one();
    }

    private SysUser getHasPasswordById(String id) {
        return lambdaQuery()
                .eq(SysUser::getUId, id)
                .eq(SysUser::getDeleteFlag, false)
                .one();
    }

    /**
     * 检查用户名是否存在
     */
    private boolean checkUserName(String userName) {
        return lambdaQuery()
                .eq(SysUser::getUserNameHash, AESUtil.hash(userName))
                .eq(SysUser::getDeleteFlag, false)
                .exists();
    }
}
