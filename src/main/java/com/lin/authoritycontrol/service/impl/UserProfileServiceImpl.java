package com.lin.authoritycontrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.controller.sys.user.form.UserSaveForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserUpdateForm;
import com.lin.authoritycontrol.mapper.UserProfileMapper;
import com.lin.authoritycontrol.mapper.domain.UserProfile;
import com.lin.authoritycontrol.service.UserProfileService;
import com.lin.authoritycontrol.util.AESUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 用户个人信息service实现
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProfile(String userId, UserSaveForm user) {
        UserProfile profile = new UserProfile(userId, user);
        profile.setIdCard(AESUtil.encrypt(user.getIdCard()));
        profile.setIdCardHash(AESUtil.hash(user.getIdCard()));
        profile.setCreatorId(""); // todo 暂时置空
        profile.setCreateTime(LocalDateTime.now());
        save(profile);
    }

    @Override
    public void updateProfile(UserUpdateForm user) {
        UserProfile profile = new UserProfile(user.getUserId(), user);
        profile.setIdCard(AESUtil.encrypt(user.getIdCard()));
        profile.setIdCardHash(AESUtil.hash(user.getIdCard()));
        profile.setModifierId(""); // todo 暂时置空
        profile.setModifyTime(LocalDateTime.now());
        updateById(profile);
    }
}
