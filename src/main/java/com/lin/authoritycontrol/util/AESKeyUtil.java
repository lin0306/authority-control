package com.lin.authoritycontrol.util;

import cn.hutool.extra.spring.SpringUtil;
import com.lin.authoritycontrol.common.config.SysParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

/**
 * AES 秘钥工具类
 *
 * @author 林维家
 * @since 2024/12/28 下午3:12
 */
@Slf4j
@Component
public class AESKeyUtil {

    private AESKeyUtil() {

    }

    private static final String ALGORITHM = "AES";
    private static final SecretKey SECRET_KEY;
    private static final String KEY_FILE_PATH;

    static {
        SysParam sysParam = SpringUtil.getBean(SysParam.class);
        KEY_FILE_PATH = sysParam.getAesFilePath();
        SecretKey key = loadKeyFromFile();
        if (key == null) {
            key = generateKey();
            saveKeyToFile(key);
        }
        SECRET_KEY = key;
        log.info("AES 秘钥完成");
    }

    /**
     * 获取aes秘钥
     */
    public static SecretKey getSecretKey() {
        return SECRET_KEY;
    }

    private static SecretKey generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(128);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("秘钥生成失败", e);
        }
    }

    private static void saveKeyToFile(SecretKey key) {
        try (FileOutputStream fos = new FileOutputStream(KEY_FILE_PATH)) {
            fos.write(key.getEncoded());
        } catch (IOException e) {
            throw new RuntimeException("秘钥保存失败", e);
        }
    }

    private static SecretKey loadKeyFromFile() {
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(KEY_FILE_PATH));
            return new SecretKeySpec(keyBytes, ALGORITHM);
        } catch (IOException e) {
            return null; // Key file does not exist or cannot be read
        }
    }
}
