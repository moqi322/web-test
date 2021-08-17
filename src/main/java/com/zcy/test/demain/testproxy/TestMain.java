package com.zcy.test.demain.testproxy;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;


/**
 * 生成公钥保存到pkcs8_public_key.pem文件中
 * 私钥保存在表access_private_key中
 *
 * 生成uuid保存在app.properties中，和表access_app_info中
 */
public class TestMain {
    // 数字签名，密钥算法
    private static final String RSA_KEY_ALGORITHM = "RSA";
    // RSA密钥长度
    private static final int KEY_SIZE = 1024;

    public static void main(String[] args) throws Exception {
        createKey();
        createUUID();

    }
  
    /**
     * 创建密钥文件
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static void  createKey() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
        SecureRandom secrand = new SecureRandom();
        // 初始化随机产生器
        secrand.setSeed("zcy".getBytes());
        // 初始化密钥生成器
        keygen.initialize(KEY_SIZE, secrand);
        KeyPair keys = keygen.genKeyPair();
        String pub_key = Base64.encodeBase64String(keys.getPublic().getEncoded());
        String pri_key = Base64.encodeBase64String(keys.getPrivate().getEncoded());

        System.out.println("公钥："+pub_key);
        System.out.println("私钥："+pri_key);
    }

    public static void createUUID(){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }
}
