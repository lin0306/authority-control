package com.lin.authoritycontrol.common.enums.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 日志业务类型枚举
 *
 * @author 林维家
 * @since 2024/12/28 下午6:55
 */
@Getter
@AllArgsConstructor
public enum LogRelTypeEnum {

    // =========================== 用户管理 start ===========================

    USER_CREATE("user_create", "用户创建"),
    PWD_UPDATE("pwd_update", "修改密码"),
    PROFILE_UPDATE("profile_update", "修改个人信息"),
    ROLE_ADD("role_add", "添加用户角色"),
    ROLE_DELETE("role_delete", "删除用户角色"),
    USER_DELETE("user_delete", "用户删除"),

    // =========================== 用户管理 end ===========================

    ;

    final String code;
    final String name;

    public static LogRelTypeEnum getByCode(String code) {
        return Arrays.stream(LogRelTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
