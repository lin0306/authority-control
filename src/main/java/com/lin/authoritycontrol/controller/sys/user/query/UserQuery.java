package com.lin.authoritycontrol.controller.sys.user.query;

import com.lin.authoritycontrol.common.base.PageForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

}
