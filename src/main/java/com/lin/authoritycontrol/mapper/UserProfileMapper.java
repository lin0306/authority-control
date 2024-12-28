package com.lin.authoritycontrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.authoritycontrol.mapper.domain.UserProfile;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户个人信息
 *
 * @author 林维家
 * @since 2024/12/28 下午4:28
 */
@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfile> {
}