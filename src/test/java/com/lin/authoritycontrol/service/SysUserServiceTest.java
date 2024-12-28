package com.lin.authoritycontrol.service;

import cn.hutool.json.JSONUtil;
import com.lin.authoritycontrol.controller.sys.user.form.UserForm;
import com.lin.authoritycontrol.controller.sys.user.query.UserQuery;
import com.lin.authoritycontrol.controller.sys.user.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 林维家
 * @since 2024/12/28 下午1:39
 */
@SpringBootTest
class SysUserServiceTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    void test() {
        UserForm form = new UserForm();
        form.setUserName("lin");
        form.setPassword("123456");
        sysUserService.saveUser(form);

        UserQuery query = new UserQuery();
        List<UserVO> voList = sysUserService.getUserList(query);
        System.out.println(JSONUtil.toJsonStr(voList));

        query.setUserName("l");
        voList = sysUserService.getUserList(query);
        System.out.println(JSONUtil.toJsonStr(voList));

        query.setUserName("i");
        voList = sysUserService.getUserList(query);
        System.out.println(JSONUtil.toJsonStr(voList));

        query.setUserName("n");
        voList = sysUserService.getUserList(query);
        System.out.println(JSONUtil.toJsonStr(voList));

        query.setUserName("li");
        voList = sysUserService.getUserList(query);
        System.out.println(JSONUtil.toJsonStr(voList));

        query.setUserName("ln");
        voList = sysUserService.getUserList(query);
        System.out.println(JSONUtil.toJsonStr(voList));

        query.setUserName("in");
        voList = sysUserService.getUserList(query);
        System.out.println(JSONUtil.toJsonStr(voList));

        query.setUserName("lin");
        voList = sysUserService.getUserList(query);
        System.out.println(JSONUtil.toJsonStr(voList));
    }

}