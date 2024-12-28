package com.lin.authoritycontrol.controller.sys.user;

import com.lin.authoritycontrol.RespCheck;
import com.lin.authoritycontrol.common.base.PageVO;
import com.lin.authoritycontrol.common.base.Result;
import com.lin.authoritycontrol.controller.sys.user.form.PasswordUpdateForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserSaveForm;
import com.lin.authoritycontrol.controller.sys.user.form.UserUpdateForm;
import com.lin.authoritycontrol.controller.sys.user.query.UserQuery;
import com.lin.authoritycontrol.controller.sys.user.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author 林维家
 * @since 2024/12/28 下午5:32
 */
@SpringBootTest
class UserControllerTest {

    @Resource
    private UserController userController;

    @Test
    void test() {
        UserSaveForm form = new UserSaveForm();
        form.setUserName("lin");
        form.setPassword("123456");
        form.setRealName("lin");
        form.setGender(1);
        form.setIdCard("123456789012345678");
        form.setIdCardType("123");
        form.setRoleIds(Collections.singletonList("123"));
        Result<Void> result = userController.saveUser(form);
        RespCheck.checkCode(result, true);

        UserQuery query = new UserQuery();
        Result<PageVO<UserVO>> result1 = userController.queryUserPage(query);
        RespCheck.checkData(result1, true);

        String userId = result1.getData().getRecords().get(0).getUId();

        query.setUserName("l");
        query.setGender(1);
        query.setRealName("l");
        query.setIdCard("123456789");
        query.setIdCardType("123");
        result1 = userController.queryUserPage(query);
        RespCheck.checkData(result1, true);

        UserUpdateForm updateForm = new UserUpdateForm();
        updateForm.setRealName("llllll");
        updateForm.setGender(1);
        updateForm.setIdCard("123456789012345678");
        updateForm.setIdCardType("123");
        updateForm.setRoleIds(Collections.singletonList("123"));
        Result<Void> result2 = userController.updateUser(userId, updateForm);
        RespCheck.checkCode(result2, true);

        PasswordUpdateForm passwordUpdateForm = new PasswordUpdateForm();
        passwordUpdateForm.setPassword("123456");
        passwordUpdateForm.setRealName("llllll");
        passwordUpdateForm.setGender(1);
        passwordUpdateForm.setIdCard("123456789012345678");
        passwordUpdateForm.setIdCardType("123");
        Result<Void> result6 = userController.updateUserAndPassword(userId, passwordUpdateForm);
        RespCheck.checkCode(result6, true);

        Result<UserVO> result3 = userController.getUser(userId);
        RespCheck.checkData(result3, true);

        Result<Void> result4 = userController.deleteUser(userId);
        RespCheck.checkCode(result4, true);

        Result<UserVO> result5 = userController.getUser(userId);
        RespCheck.checkData(result5, false);
    }

}