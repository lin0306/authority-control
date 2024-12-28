package com.lin.authoritycontrol.controller.sys.user.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class PasswordUpdateForm extends UserUpdateForm implements Serializable {

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 20, message = "密码长度为6-20位")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$", message = "请填写同时包含数字、字母，长度在6-20位的密码")
    private String password;
}
