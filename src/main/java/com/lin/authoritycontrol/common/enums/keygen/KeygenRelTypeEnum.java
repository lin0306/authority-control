package com.lin.authoritycontrol.common.enums.keygen;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 秘钥业务类型枚举
 *
 * @author 林维家
 * @since 2024/12/30 20:41
 */
@Getter
@AllArgsConstructor
public enum KeygenRelTypeEnum {

    LOGIN_KEY("login_key", "登录秘钥"),

    ;

    final String code;
    final String name;

    public static KeygenRelTypeEnum getByCode(String code) {
        return Arrays.stream(KeygenRelTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
