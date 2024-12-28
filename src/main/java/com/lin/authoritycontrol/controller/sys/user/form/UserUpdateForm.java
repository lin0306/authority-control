package com.lin.authoritycontrol.controller.sys.user.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserUpdateForm implements Serializable {

    /**
     * 用户id
     */
    private String userId;

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
