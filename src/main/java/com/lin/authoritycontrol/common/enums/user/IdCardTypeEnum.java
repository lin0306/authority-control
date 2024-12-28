package com.lin.authoritycontrol.common.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 身份卡类型枚举
 *
 * @author 林维家
 * @since 2024/12/28 下午7:23
 */
@Getter
@AllArgsConstructor
public enum IdCardTypeEnum {

    ;

    final String code;
    final String name;

    public static IdCardTypeEnum getByCode(String code) {
        return Arrays.stream(IdCardTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
