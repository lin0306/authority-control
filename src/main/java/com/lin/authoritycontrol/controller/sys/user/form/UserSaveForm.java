package com.lin.authoritycontrol.controller.sys.user.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表单
 *
 * @author 林维家
 * @since 2024/12/28 下午12:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveForm implements Serializable {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 16, message = "用户名长度为4-16位")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "用户名只能包含字母和数字，长度在4-16位")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 20, message = "密码长度为6-20位")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$", message = "请填写同时包含数字、字母，长度在6-20位的密码")
    private String password;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别 0-女，1-男
     */
    private Integer gender;

    /**
     * 身份卡
     */
    private String idCard;

    /**
     * 身份卡类型
     */
    private String idCardType;

    /**
     * 角色id列表
     */
    private List<String> roleIds;
}
