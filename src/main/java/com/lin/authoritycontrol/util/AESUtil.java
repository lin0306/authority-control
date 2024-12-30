package com.lin.authoritycontrol.util;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.lin.authoritycontrol.common.exception.CipherException;

import java.nio.charset.StandardCharsets;
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

    /**
     * 数据加密
     */
    public static String encrypt(String plainText) {
        if (plainText == null) {
            return null;
        }
        try {
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, AESKeyUtil.getSecretKey());
            //加密
            byte[] encryptedBytes = aes.encrypt(plainText);
//            Cipher cipher = Cipher.getInstance(CipherConstant.ALGORITHM_AES_CBC_PKCS5);
//            cipher.init(Cipher.ENCRYPT_MODE, AESKeyUtil.getSecretKey());
//            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
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
            SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, AESKeyUtil.getSecretKey());
            //解密
            byte[] decryptBytes = aes.decrypt(encryptedText);
//            Cipher cipher = Cipher.getInstance(CipherConstant.ALGORITHM_AES_CBC_PKCS5);
//            cipher.init(Cipher.DECRYPT_MODE, AESKeyUtil.getSecretKey());
//            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            return new String(decryptBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new CipherException("Error decrypting data", e);
        }
    }

    /**
     * 字符串转成hash值，用于需要进行模糊搜索的数据
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

    public static void main(String[] args) {
        String plainText = "123456";
        String encryptedText = encrypt(plainText);
        System.out.println("加密后的数据：" + encryptedText);
        String decryptedText = decrypt(encryptedText);
        System.out.println("解密后的数据：" + decryptedText);
        System.out.println("加密再解密后的数据是否一致：" + (plainText.equals(decryptedText)));
    }
}
