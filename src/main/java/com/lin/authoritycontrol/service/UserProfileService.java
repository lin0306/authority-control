package com.lin.authoritycontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.authoritycontrol.controller.sys.user.form.UserSaveForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserUpdateForm;
import com.lin.authoritycontrol.mapper.domain.UserProfile;

/**
 * 用户个人信息
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
public interface UserProfileService extends IService<UserProfile> {

    /**
     * 保存用户个人信息
     *
     * @param userId 用户id
     * @param user   用户信息
     */
    void saveProfile(String userId, UserSaveForm user);

    /**
     * 更新用户个人信息
     *
     * @param user 用户信息
     */
    void updateProfile(UserUpdateForm user);
}
