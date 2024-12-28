package com.lin.authoritycontrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.mapper.UserProfileMapper;
import com.lin.authoritycontrol.mapper.domain.UserProfile;
import com.lin.authoritycontrol.service.UserProfileService;
import org.springframework.stereotype.Service;

/**
 * 用户个人信息service实现
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Service
public class UserProfileServiceImpl extends ServiceImpl<UserProfileMapper, UserProfile> implements UserProfileService {

}
