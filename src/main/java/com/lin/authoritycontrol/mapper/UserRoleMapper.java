package com.lin.authoritycontrol.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lin.authoritycontrol.controller.sys.role.vo.RoleVO;
import com.lin.authoritycontrol.controller.sys.user.vo.UserRoleVO;
import com.lin.authoritycontrol.mapper.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色
 *
 * @author 林维家
 * @since 2024/12/28 下午4:29
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 获取用户角色
     *
     * @param userId 用户id
     * @return 用户角色
     */
    List<RoleVO> getUserRoles(@Param("userId") String userId);

    /**
     * 获取用户角色
     *
     * @param userId 用户id
     * @return 用户角色
     */
    List<UserRoleVO> getUserRoleList(@Param("userId") String userId);
}