package com.lin.authoritycontrol.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 林维家
 * @since 2024/12/30 19:19
 */
@SpringBootTest
class AESUtilTest {

    @Test
    void test() {
        String plainText = "123456";
        String encryptedText = AESUtil.encrypt(plainText);
        System.out.println("加密后的数据：" + encryptedText);
        String decryptedText = AESUtil.decrypt(encryptedText);
        System.out.println("解密后的数据：" + decryptedText);
        System.out.println("加密再解密后的数据是否一致：" + (plainText.equals(decryptedText)));
    }

}