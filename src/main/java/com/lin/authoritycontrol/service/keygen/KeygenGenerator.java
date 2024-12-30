package com.lin.authoritycontrol.service.keygen;

import com.lin.authoritycontrol.common.enums.keygen.KeygenTypeEnum;
import com.lin.authoritycontrol.common.exception.CustomException;
import com.lin.authoritycontrol.mapper.domain.KeygenConfig;
import com.lin.authoritycontrol.util.AESKeyUtil;
import com.lin.authoritycontrol.util.ECDHKeyUtil;
import com.lin.authoritycontrol.util.RSAKeyUtil;

import java.security.KeyPair;
import java.time.LocalDateTime;
import java.util.Base64;

/**
 * 秘钥生成器
 *
 * @author 林维家
 * @since 2024/12/30 22:39
 */
public class KeygenGenerator {

    /**
     * 生成秘钥
     */
    public static void generate(KeygenConfig config, boolean isThrow) {
        KeygenTypeEnum typeEnum = KeygenTypeEnum.getByCode(config.getKeyType());
        if (typeEnum == null) {
            if (isThrow) {
                throw new CustomException("秘钥类型暂不支持");
            } else {
                return;
            }
        }
        switch (typeEnum) {
            case AES:
                // 设置在公钥上，方便后续获取
                config.setPublicKey(AESKeyUtil.genKey());
                config.setLastGenerateTime(LocalDateTime.now());
                break;
            case RSA:
                KeyPair rsaKeyPair = RSAKeyUtil.getKeyPair();
                config.setPublicKey(Base64.getEncoder().encodeToString(rsaKeyPair.getPublic().getEncoded()));
                config.setPrivateKey(Base64.getEncoder().encodeToString(rsaKeyPair.getPrivate().getEncoded()));
                config.setLastGenerateTime(LocalDateTime.now());
                break;
            case ECDH:
                KeyPair ecdhKeyPair = ECDHKeyUtil.getKeyPair();
                config.setPublicKey(Base64.getEncoder().encodeToString(ecdhKeyPair.getPublic().getEncoded()));
                config.setPrivateKey(Base64.getEncoder().encodeToString(ecdhKeyPair.getPrivate().getEncoded()));
                config.setLastGenerateTime(LocalDateTime.now());
                break;
            default:
                if (isThrow) {
                    throw new CustomException("秘钥类型暂不支持");
                } else {
                    return;
                }
        }
    }
}
