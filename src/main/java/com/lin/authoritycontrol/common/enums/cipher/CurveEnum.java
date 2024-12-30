package com.lin.authoritycontrol.common.enums.cipher;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 曲线枚举
 *
 * @author 林维家
 * @since 2024/12/30 20:23
 */
@Getter
@AllArgsConstructor
public enum CurveEnum {
    /**
     * 安全级别：128位
     * 适用场景：广泛使用，性能良好，适用于大多数安全需求。
     */
    P_256("secp256r1"),
    /**
     * 安全级别：192位
     * 适用场景：适用于需要更高安全级别的场景。
     */
    P_384("secp384r1"),
    /**
     * 安全级别：256位
     * 适用场景：适用于需要最高安全级别的场景，但性能相对较低。
     */
    P_521("secp521r1"),

    ;

    private final String value;
}
