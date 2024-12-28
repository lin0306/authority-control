package com.lin.authoritycontrol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.controller.sys.user.form.UserSaveForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserUpdateForm;
import com.lin.authoritycontrol.controller.sys.user.query.UserQuery;
import com.lin.authoritycontrol.controller.sys.user.vo.UserVO;
import com.lin.authoritycontrol.mapper.domain.SysUser;

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
    void saveUser(UserSaveForm user);

    /**
     * 修改用户
     */
    void updateUser(UserUpdateForm user);

    /**
     * 修改密码
     */
    void updatePassword(String userId, String password);

    /**
     * 删除用户
     */
    void deleteUser(String userId);

    /**
     * 分页查询用户
     */
    PageVO<UserVO> queryUserPage(UserQuery query);

    /**
     * 查询用户详细信息
     */
    UserVO getUser(String userId);
}
