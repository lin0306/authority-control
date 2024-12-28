package com.lin.authoritycontrol.util;

import com.lin.authoritycontrol.common.exception.CipherException;

import javax.crypto.Cipher;
import java.util.Base64;

/**
 * AES工具类
 *
 * @author 林维家
 * @since 2024/12/28 下午12:46
 */
public class AESUtil {

    private AESUtil() {

    }

    private static final String ALGORITHM = "AES";
    private static final String CHARSET = "UTF-8";

    /**
     * 数据加密
     */
    public static String encrypt(String plainText) {
        if (plainText == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, AESKeyUtil.getSecretKey());
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(CHARSET));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new CipherException("Error encrypting data", e);
        }
    }

    /**
     * 数据解密
     */
    public static String decrypt(String encryptedText) {
        if (encryptedText == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, AESKeyUtil.getSecretKey());
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            return new String(cipher.doFinal(decodedBytes), CHARSET);
        } catch (Exception e) {
            throw new CipherException("Error decrypting data", e);
        }
    }

    /**
     * 字符串转成hash值，用户需要进行模糊搜索参数
     */
    public static String hash(String value) {
        if (value == null) {
            return null;
        }
        char[] charArray = value.toCharArray();
        StringBuilder hashStr = new StringBuilder();
        for (char c : charArray) {
            if (hashStr.length() > 0) {
                hashStr.append("+");
            }
            hashStr.append(hash(c));
        }
        return hashStr.toString();
    }

    /**
     * 将单个字符转成hash值
     */
    private static String hash(char value) {
        return Integer.toHexString(String.valueOf(value).hashCode());
    }
}
