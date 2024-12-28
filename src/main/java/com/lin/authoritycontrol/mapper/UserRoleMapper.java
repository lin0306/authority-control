package com.lin.authoritycontrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.authoritycontrol.mapper.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色
 *
 * @author 林维家
 * @since 2024/12/28 下午4:29
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}