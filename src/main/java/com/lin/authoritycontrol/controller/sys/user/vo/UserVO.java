package com.lin.authoritycontrol.controller.sys.user.vo;

import com.lin.authoritycontrol.controller.sys.role.vo.RoleVO;
import com.lin.authoritycontrol.mapper.domain.SysUser;
import com.lin.authoritycontrol.mapper.domain.UserProfile;
import com.lin.authoritycontrol.util.AESUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别 0-女，1-男
     */
    private Integer gender;

    /**
     * 性别
     */
    private Integer genderValue;

    /**
     * 身份卡
     */
    private String idCard;

    /**
     * 身份卡类型
     */
    private String idCardType;

    /**
     * 身份卡类型
     */
    private String idCardTypeValue;

    /**
     * 角色id列表
     */
    private List<RoleVO> roleIds;

    public UserVO(SysUser sysUser, UserProfile profile, List<RoleVO> roleList) {
        this.uId = sysUser.getUId();
        this.userName = AESUtil.decrypt(sysUser.getUserName());
        this.realName = profile.getRealName();
        this.gender = profile.getGender();
        this.idCard = AESUtil.decrypt(profile.getIdCard());
        this.idCardType = profile.getIdCardType();
        this.roleIds = roleList;
    }
}
