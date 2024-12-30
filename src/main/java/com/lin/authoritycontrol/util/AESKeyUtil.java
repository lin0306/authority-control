package com.lin.authoritycontrol.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.extra.spring.SpringUtil;
import com.lin.authoritycontrol.common.config.SysParam;
import com.lin.authoritycontrol.common.constants.CipherConstant;
import com.lin.authoritycontrol.common.exception.CipherException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    private static final SecretKey SECRET_KEY;
    private static String KEY_FILE_PATH;

    static {
        try {
            SysParam sysParam = SpringUtil.getBean(SysParam.class);
            KEY_FILE_PATH = sysParam.getAesFilePath();
        } catch (Exception e) {
            // 没有使用spring boot方式启动，拿不到配置文件的数据，直接设置为项目根目录
            KEY_FILE_PATH = new File("aes.key").getAbsolutePath();
        }
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

    /**
     * 生成秘钥
     */
    private static SecretKey generateKey() {
        // 128位：使用 16 字节的密钥，是最常用的密钥长度，提供了足够的安全性，并且性能较好。
        // 192位：使用 24 字节的密钥，提供了更高的安全性，但性能略低于128位。
        // 256位：使用 32 字节的密钥，提供了最高级别的安全性，但性能相对较低。
        byte[] keyBytes = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), 256).getEncoded();
        return new SecretKeySpec(keyBytes, CipherConstant.ALGORITHM_AES);
    }

    /**
     * 将秘钥保存到文件
     */
    private static void saveKeyToFile(SecretKey key) {
        File file = new File(KEY_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(key.getEncoded());
        } catch (IOException e) {
            throw new CipherException("秘钥保存失败", e);
        }
    }

    /**
     * 根据文件绝对路径读取秘钥
     */
    private static SecretKey loadKeyFromFile() {
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(KEY_FILE_PATH));
            return new SecretKeySpec(keyBytes, CipherConstant.ALGORITHM_AES);
        } catch (IOException e) {
            // 文件不存在或读取失败
            return null;
        }
    }
}
