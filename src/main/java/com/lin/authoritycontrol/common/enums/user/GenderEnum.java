package com.lin.authoritycontrol.common.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 性别枚举
 *
 * @author 林维家
 * @since 2024/12/28 下午7:19
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    MALE(1, "男"),
    FEMALE(0, "女"),
    UNKNOWN(-1, "未知"),
    ;

    final int code;
    final String name;

    public static GenderEnum getByCode(Integer code) {
        if (code == null) {
            return GenderEnum.UNKNOWN;
        }
        return Arrays.stream(GenderEnum.values())
                .filter(e -> e.getCode() == code)
                .findAny()
                .orElse(GenderEnum.UNKNOWN);
    }
}
