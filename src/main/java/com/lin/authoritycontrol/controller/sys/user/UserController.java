package com.lin.authoritycontrol.controller.sys.user;

import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.common.base.Result;
import com.lin.authoritycontrol.controller.sys.user.form.PasswordUpdateForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserSaveForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserUpdateForm;
import com.lin.authoritycontrol.controller.sys.user.query.UserQuery;
import com.lin.authoritycontrol.controller.sys.user.vo.UserVO;
import com.lin.authoritycontrol.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author 林维家
 * @since 2024/12/27 下午9:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private final SysUserService sysUserService;

    /**
     * 用户新增
     */
    @PostMapping
    public Result<Void> saveUser(@Validated @RequestBody UserSaveForm user) {
        sysUserService.saveUser(user);
        return Result.success();
    }

    /**
     * 用户修改
     */
    @PutMapping("{userId}/profile")
    public Result<Void> updateUser(@RequestParam String userId, @Validated @RequestBody UserUpdateForm user) {
        user.setUserId(userId);

        return Result.success();
    }

    /**
     * 用户修改
     */
    @PutMapping("{userId}/profile-pwd")
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> updateUserAndPassword(@RequestParam String userId, @Validated @RequestBody PasswordUpdateForm user) {
        user.setUserId(userId);
        sysUserService.updateUser(user);
        sysUserService.updatePassword(userId, user.getPassword());
        return Result.success();
    }

    /**
     * 用户删除
     */
    @DeleteMapping("{userId}")
    public Result<Void> deleteUser(@PathVariable String userId) {
        sysUserService.deleteUser(userId);
        return Result.success();
    }

    /**
     * 用户列表
     */
    @GetMapping
    public Result<PageVO<UserVO>> queryUserPage(UserQuery query) {
        return Result.success(sysUserService.queryUserPage(query));
    }

    /**
     * 用户详情
     */
    @GetMapping("{userId}")
    public Result<UserVO> getUser(@PathVariable String userId) {
        return Result.success(sysUserService.getUser(userId));
    }
}
