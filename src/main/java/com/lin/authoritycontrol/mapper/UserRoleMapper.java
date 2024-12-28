package com.lin.authoritycontrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.authoritycontrol.mapper.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色mapper
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {
}