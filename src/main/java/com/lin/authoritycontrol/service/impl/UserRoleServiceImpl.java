package com.lin.authoritycontrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.authoritycontrol.mapper.UserRoleMapper;
import com.lin.authoritycontrol.mapper.domain.UserRole;
import com.lin.authoritycontrol.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色service实现
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
