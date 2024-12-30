package com.lin.authoritycontrol.common.enums.keygen;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 秘钥类型枚举
 *
 * @author 林维家
 * @since 2024/12/30 21:48
 */
@Getter
@AllArgsConstructor
public enum KeygenTypeEnum {
    // 对称加密秘钥
    AES("AES", "AES"),
    DES("DES", "DES（未实现）"),
    SM4("SM4", "SM4（未实现）"),

    // 摘要算法
    MD2("MD2", "MD2（未实现）"),
    MD5("MD5", "MD5（未实现）"),
    SHA1("SHA-1", "SHA-1（未实现）"),
    SHA256("SHA-256", "SHA-256（未实现）"),
    SHA384("SHA-384", "SHA-384（未实现）"),
    SHA512("SHA-512", "SHA-512（未实现）"),
    SM3("SM3", "SM3（未实现）"),

    // 消息认证码,
    HMAC_MD_5("HmacMD5", "HmacMD5（未实现）"),
    HMAC_SHA_1("HmacSHA1", "HmacSHA1（未实现）"),
    HMAC_SHA_256("HmacSHA256", "HmacSHA256（未实现）"),
    HMAC_SHA_384("HmacSHA384", "HmacSHA384（未实现）"),
    HMAC_SHA_512("HmacSHA512", "HmacSHA512（未实现）"),
    HASH("HASH", "HASH（未实现）"),

    // 非对称加密秘钥,
    RSA("RSA", "RSA"),
    ECDH("ECDH", "ECDH"),
    SM2("SM2", "SM2（未实现）"),

    ;

    final String code;
    final String name;

    public static KeygenTypeEnum getByCode(String code) {
        return Arrays.stream(KeygenTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
