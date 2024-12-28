package com.lin.authoritycontrol.controller.sys.user.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息表单
 *
 * @author 林维家
 * @since 2024/12/28 下午12:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
