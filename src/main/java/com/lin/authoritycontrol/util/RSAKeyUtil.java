package com.lin.authoritycontrol.util;

import cn.hutool.crypto.SecureUtil;
import com.lin.authoritycontrol.common.constants.CipherConstant;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * RSA 秘钥工具类
 *
 * @author 林维家
 * @since 2024/12/30 17:18
 */
public class RSAKeyUtil {

    private RSAKeyUtil() {

    }

    public static KeyPair getKeyPair() {
        return SecureUtil.generateKeyPair(CipherConstant.ALGORITHM_RSA_ECB_PKCS1);
    }

    public static void main(String[] args) {
        KeyPair keyPair = getKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        PublicKey publicKey = keyPair.getPublic();
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
    }
}
