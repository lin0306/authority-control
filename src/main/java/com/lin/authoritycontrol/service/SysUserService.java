package com.lin.authoritycontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.authoritycontrol.controller.sys.user.form.UserForm;
import com.lin.authoritycontrol.controller.sys.user.query.UserQuery;
import com.lin.authoritycontrol.controller.sys.user.vo.UserVO;
import com.lin.authoritycontrol.mapper.domain.SysUser;

import java.util.List;

/**
 * 用户service
 *
 * @author 林维家
 * @since 2024/12/27 下午9:07
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 添加用户
     */
    void saveUser(UserForm user);

    /**
     * 查询用户列表
     */
    List<UserVO> getUserList(UserQuery query);
}
