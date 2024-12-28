package com.lin.authoritycontrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.authoritycontrol.mapper.domain.SysRoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色mapper
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleDO> {
}