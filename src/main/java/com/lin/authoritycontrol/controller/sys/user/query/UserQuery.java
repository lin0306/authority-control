package com.lin.authoritycontrol.controller.sys.user.query;

import com.lin.authoritycontrol.common.base.PageForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户查询
 *
 * @author 林维家
 * @since 2024/12/28 下午1:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery extends PageForm {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
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

}
