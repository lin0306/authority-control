package com.lin.authoritycontrol.common.enums.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 响应结果枚举
 *
 * @author 林维家
 * @since 2024/12/25 下午8:08
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS("success", "成功"),
    FAILED("failed", "失败"),

    ;

    final String code;
    final String name;

    public static ResultEnum getByCode(String code) {
        return Arrays.stream(ResultEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
