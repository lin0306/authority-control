package com.lin.authoritycontrol.common.enums.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 日志类型枚举
 *
 * @author 林维家
 * @since 2024/12/28 下午6:55
 */
@Getter
@AllArgsConstructor
public enum LogTypeEnum {

    USER("user", "用户管理"),
    
    ;

    final String code;
    final String name;

    public static LogTypeEnum getByCode(String code) {
        return Arrays.stream(LogTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
