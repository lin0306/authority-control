package com.lin.authoritycontrol.util;

import com.lin.authoritycontrol.common.constants.CipherConstant;
import com.lin.authoritycontrol.common.enums.cipher.CurveEnum;
import com.lin.authoritycontrol.common.exception.CipherException;
import org.springframework.util.StopWatch;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 * ECDH 秘钥工具类
 *
 * @author 林维家
 * @since 2024/12/30 19:03
 */
public class ECDHKeyUtil {

    private ECDHKeyUtil() {

    }

    public static KeyPair getKeyPair() {
        return getKeyPair(CurveEnum.P_384);
    }

    public static KeyPair getKeyPair(CurveEnum curve) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(CipherConstant.ALGORITHM_EC);
            keyPairGenerator.initialize(new ECGenParameterSpec(curve.getValue()));
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new CipherException("秘钥生成失败", e);
        }
    }

    /**
     * 生成aesKey，公钥和私钥需要双方提供，一方提供公钥，一方提供私钥，最终生成一个aes秘钥
     *
     * @param privateKeyStr 私钥
     * @param publicKeyStr  公钥
     * @return aes秘钥
     */
    public static SecretKey getSecretKey(String privateKeyStr, String publicKeyStr) {
        try {
            PrivateKey privateKey = getPrivateKey(privateKeyStr);
            PublicKey publicKey = getPublicKey(publicKeyStr);
            return getSecretKey(privateKey, publicKey);
        } catch (Exception e) {
            throw new CipherException("秘钥生成失败", e);
        }
    }

    /**
     * 生成aesKey，公钥和私钥需要双方提供，一方提供公钥，一方提供私钥，最终生成一个aes秘钥
     *
     * @param privateKey 私钥
     * @param publicKey  公钥
     * @return aes秘钥
     */
    public static SecretKey getSecretKey(PrivateKey privateKey, PublicKey publicKey) {
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance(CipherConstant.ALGORITHM_ECDH);
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(publicKey, true);
            byte[] sharedSecret = keyAgreement.generateSecret();
            // 将共享密钥转换为AES密钥
            // 秘钥长度可选参数：16字节（128位）、24字节（192位）或32字节（256位）
            return new SecretKeySpec(sharedSecret, 0, 24, CipherConstant.ALGORITHM_AES);
        } catch (Exception e) {
            throw new CipherException("秘钥生成失败", e);
        }
    }

    /**
     * 将私钥字符串转成私钥对象
     */
    private static PrivateKey getPrivateKey(String privateKeyStr) {
        try {
            byte[] encoded = Base64.getDecoder().decode(privateKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(CipherConstant.ALGORITHM_EC);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new CipherException("将私钥字符串转换成私钥失败", e);
        }
    }

    private static PublicKey getPublicKey(String publicKeyStr) {
        try {
            byte[] encoded = Base64.getDecoder().decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(CipherConstant.ALGORITHM_EC);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new CipherException("将公钥字符串转换成公钥失败", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("调用无参方法:");
        KeyPair keyPairA = getKeyPair();
        KeyPair keyPairB = getKeyPair();

        SecretKey secretKeyA = getSecretKey(keyPairA.getPrivate(), keyPairB.getPublic());
        SecretKey secretKeyB = getSecretKey(keyPairB.getPrivate(), keyPairA.getPublic());

        System.out.println("A生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyA.getEncoded()));
        System.out.println("B生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyB.getEncoded()));
        System.out.println("生成的共享公钥是否一致：" + (Objects.equals(Base64.getEncoder().encodeToString(secretKeyA.getEncoded()), Base64.getEncoder().encodeToString(secretKeyB.getEncoded()))));
        System.out.println("====================================");
        StopWatch watch = new StopWatch();
        watch.start("secp256r1");
        System.out.println("secp256r1:");
        keyPairA = getKeyPair(CurveEnum.P_256);
        keyPairB = getKeyPair(CurveEnum.P_256);

        secretKeyA = getSecretKey(keyPairA.getPrivate(), keyPairB.getPublic());
        secretKeyB = getSecretKey(keyPairB.getPrivate(), keyPairA.getPublic());

        System.out.println("A生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyA.getEncoded()));
        System.out.println("B生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyB.getEncoded()));
        System.out.println("生成的共享公钥是否一致：" + (Objects.equals(Base64.getEncoder().encodeToString(secretKeyA.getEncoded()), Base64.getEncoder().encodeToString(secretKeyB.getEncoded()))));
        System.out.println("====================================");
        watch.stop();
        watch.start("secp384r1");
        System.out.println("secp384r1:");
        keyPairA = getKeyPair(CurveEnum.P_384);
        keyPairB = getKeyPair(CurveEnum.P_384);

        secretKeyA = getSecretKey(keyPairA.getPrivate(), keyPairB.getPublic());
        secretKeyB = getSecretKey(keyPairB.getPrivate(), keyPairA.getPublic());

        System.out.println("A生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyA.getEncoded()));
        System.out.println("B生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyB.getEncoded()));
        System.out.println("生成的共享公钥是否一致：" + (Objects.equals(Base64.getEncoder().encodeToString(secretKeyA.getEncoded()), Base64.getEncoder().encodeToString(secretKeyB.getEncoded()))));
        System.out.println("====================================");
        watch.stop();
        watch.start("secp521r1");
        System.out.println("secp521r1:");
        keyPairA = getKeyPair(CurveEnum.P_521);
        keyPairB = getKeyPair(CurveEnum.P_521);

        secretKeyA = getSecretKey(keyPairA.getPrivate(), keyPairB.getPublic());
        secretKeyB = getSecretKey(keyPairB.getPrivate(), keyPairA.getPublic());

        System.out.println("A生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyA.getEncoded()));
        System.out.println("B生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyB.getEncoded()));
        System.out.println("生成的共享公钥是否一致：" + (Objects.equals(Base64.getEncoder().encodeToString(secretKeyA.getEncoded()), Base64.getEncoder().encodeToString(secretKeyB.getEncoded()))));
        System.out.println("====================================");
        watch.stop();
        System.out.println(watch.prettyPrint());

        System.out.println("secp256r1（字符串）:");
        keyPairA = getKeyPair(CurveEnum.P_256);
        keyPairB = getKeyPair(CurveEnum.P_256);

        secretKeyA = getSecretKey(Base64.getEncoder().encodeToString(keyPairA.getPrivate().getEncoded()), Base64.getEncoder().encodeToString(keyPairB.getPublic().getEncoded()));
        secretKeyB = getSecretKey(keyPairB.getPrivate(), keyPairA.getPublic());

        System.out.println("A生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyA.getEncoded()));
        System.out.println("B生成的共享秘钥：" + Base64.getEncoder().encodeToString(secretKeyB.getEncoded()));
        System.out.println("生成的共享公钥是否一致：" + (Objects.equals(Base64.getEncoder().encodeToString(secretKeyA.getEncoded()), Base64.getEncoder().encodeToString(secretKeyB.getEncoded()))));
        System.out.println("====================================");
    }
}
