package com.lin.authoritycontrol.controller.sys.user.vo;

import com.lin.authoritycontrol.mapper.domain.SysUserDO;
import com.lin.authoritycontrol.util.AESUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 用户vo
 *
 * @author 林维家
 * @since 2024/12/28 下午1:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    /**
     * 用户id
     */
    private String uId;

    /**
     * 用户名
     */
    private String userName;

    public UserVO(SysUserDO sysUserDO) {
        this.uId = sysUserDO.getUId();
        this.userName = AESUtil.decrypt(sysUserDO.getUserName());
    }
}
