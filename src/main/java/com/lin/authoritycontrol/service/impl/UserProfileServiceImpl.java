package com.lin.authoritycontrol.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.common.enums.log.LogRelTypeEnum;
import com.lin.authoritycontrol.common.enums.log.LogTypeEnum;
import com.lin.authoritycontrol.controller.sys.user.form.UserSaveForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserUpdateForm;
import com.lin.authoritycontrol.mapper.UserProfileMapper;
import com.lin.authoritycontrol.mapper.domain.UserProfile;
import com.lin.authoritycontrol.service.SysLogService;
import com.lin.authoritycontrol.service.UserProfileService;
import com.lin.authoritycontrol.util.AESUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 用户个人信息service实现
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Service
@AllArgsConstructor
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {

    private final SysLogService sysLogService;

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
        UserProfile userProfile = getById(user.getUserId());
        UserProfile profile = new UserProfile(user.getUserId(), user);
        profile.setIdCard(AESUtil.encrypt(user.getIdCard()));
        profile.setIdCardHash(AESUtil.hash(user.getIdCard()));
        profile.setModifierId(""); // todo 暂时置空
        profile.setModifyTime(LocalDateTime.now());
        updateById(profile);
        String content = packageContent(userProfile, profile);
        if (StrUtil.isNotBlank(content)) {
            sysLogService.addLog(LogTypeEnum.USER, user.getUserId(), LogRelTypeEnum.PROFILE_UPDATE, content);
        }

    }

    /**
     * 封装修改内容
     *
     * @param oldProfile 旧数据
     * @param newProfile 新数据
     */
    private static String packageContent(UserProfile oldProfile, UserProfile newProfile) {
        String content = "";
        if (!oldProfile.getRealName().equals(newProfile.getRealName())) {
            content += "姓名：" + oldProfile.getRealName() + "->" + newProfile.getRealName() + "；";
        }
        if (!Objects.equals(oldProfile.getGender(), newProfile.getGender())) {
            content += "性别：" + oldProfile.getGenderName() + "->" + newProfile.getGenderName() + "；";
        }
        if (!oldProfile.getIdCard().equals(newProfile.getIdCard())) {
            content += "身份卡号修改；";
        }
        if (!oldProfile.getIdCardType().equals(newProfile.getIdCardType())) {
            content += "身份卡类型修改：" + oldProfile.getIdCardTypeName() + "->" + newProfile.getIdCardTypeName() + "；";
        }
        return content;
    }
}
