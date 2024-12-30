package com.lin.authoritycontrol.common.enums.keygen;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 秘钥更新频率枚举
 *
 * @author 林维家
 * @since 2024/12/30 21:29
 */
@Getter
@AllArgsConstructor
public enum KeygenUpdateFrequencyEnum {
    DAILY("daily", "每天一次"),
    WEEKLY("weekly", "每周一次"),
    MONTHLY("monthly", "每月一次"),
    QUARTERLY("quarterly", "每季度一次"),
    HALF_YEARLY("half_yearly", "每半年一次"),
    YEARLY("yearly", "每年一次"),
    PERMANENT("permanent", "永久"),

    ;

    final String code;
    final String name;

    public static KeygenUpdateFrequencyEnum getByCode(String code) {
        return Arrays.stream(KeygenUpdateFrequencyEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
