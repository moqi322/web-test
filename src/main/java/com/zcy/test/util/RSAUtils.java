package com.zcy.test.util;

import com.sun.tools.javac.util.Convert;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.omg.IOP.Encoding;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.reader.StreamReader;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: dinghaijun(宿草)
 * @Description: description
 * @Date: Created in 2:16 PM 2019/7/30
 * @Modified By: Copyright(c) cai-inc.com
 */
@Slf4j
public class RSAUtils {
    private static final Map<String,PrivateKey> pkeyMap = new ConcurrentHashMap<>();

    private static PublicKey publicKey = null;
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /** */
    private static String RSA = "RSA";

    /**
     * 随机生成RSA密钥对(默认密钥长度为1024)
     */
    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(1024);
    }

    /**
     * 随机生成RSA密钥对
     *
     * @param keyLength 密钥长度，范围：512～2048<br> 一般1024
     */
    public static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            log.warn("随机生成RSA密钥对异常！e={}", e);
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
//       getPublicKey();
//        String  account="002056";   String state="A3AEnI";
//        String accountExpireTime = account  + "," + System.currentTimeMillis() + "," + state;
//        byte[] bytes = RSAUtils.encryptData(accountExpireTime.getBytes(Charset.forName("utf-8")),getPublicKey());
//        String token = URLEncoder.encode(com.sun.org.apache.xml.internal.security.utils.Base64.encode(bytes).replace("\n",""),"utf-8");
//        System.out.println(bytes+"+ ----vvvvvvvv");
//        System.out.println("----+"+token);


       String  GongyueprivateKeyce="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIrJN6r6SlFfe1JBY+yMU4sjwsVFmumrwMBwF9Q7o58HFtmcXmX4VPJmpgnCoi4y3E2UucpRdw/b8iF5EgzBu5OzEatdc62D8DFx3itbpfiMwgIhrEwlDzopVsCiNBtq9KpwgxMwuWUJP6dJaCmkKd4g9qU9LCYW/4R9lqRoUWSZAgMBAAECgYBQLNX/6/+q6qHvbt8u9BH/O3V1STSo8RO1GAxsCtgHMq08oVzXQ5KvRiV9VfcjATbLirOZ8V36x3ElbS/ENtcJLe5rIppJWBgsKOQQtpiKnZSQ6eFeu3kzADmxtcu2qEjnCCWL8E42/32tuo+5OjqwBAcFyat5OiDDcpus+BMgUQJBAMtCEr5wNboha1jNuQwxc5T0F16KLj/947nYuw3HxEuLQaQs6oZoBQW36LXu7fSnKSeSHWmEKbomLoOeT4b4BU8CQQCuzGypsABOODp5WZ/5i5v/aPzeAKfNRE31zZS+Om99+JnTzDECo4xwxMEW/k5OyoBnSo2cM4dfnxDgJWUOWs2XAkAop0qAdIkKdWy3Ek6Utb9cZ4XVDuY5Plqx7Ttcgjwsr+mtIJ63y05V2nbdDLDjYhvFImBnnluKM+DRynQ1lp3ZAkAGf/mGcdIBFpwuCQQm3mclnzzP8IxM2L5hZoyiaj/dGlJ2CsDDzVsakmZvuXMmAOe3b5FGfrAJATw6aYmReBTvAkAA4n76SwFp882zJLvKTo8w4IoUH4JvCzQb49Tdqaj7PQ8/dxr3eJ3/N8QHfTItl6httm7IZlKWwfQlSMv8a13A";
      String GongyueToken="Njc1OiXzyM1g/kxMWbMFyK7SQSe0Fd5cZQW9YISTpiAehqmsZY2TWhvcCsDDAn6B9ig47R1GIs2N4gK7r3d5z0yq4QKNL/0LYuaMokFtJKmQBetgCSODeONnLj+2nl3QCK8oklEPQtsAkpFJUYi15/nFxTu9RfRGzOOpENWPI7Y=";
       String CaiJingPrivateKeyce="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIP3xp8kCgTHF8vg1VK0uHpZbtQgthI6pHpsT0mErWdMO7LuHgbGdkQiR+G9C1fFLxrh0N1ceCYmy1HlBPYfWVkPnmQ49u5eNlB8XT9QMz4NJBtE2ufWJIffUpXUBfdEc1oGS2aFP8LMomcJT5DWg7ahJ6eAyXRcl/B31pmmLzSLAgMBAAECgYAWwx4XRPgVauHH4Lfq1BEdyV88Z4gADO1h0Fy8qY3fS17gtgGn2YN8rQ0I+rqCIRjG4jxMci6nJfnb3kzExxN58XdkASAviJUMI5Za47gLAdqiP50qhABA832+AUZCp76SjHw1qjsiIyPz7j26c7+Gy+S5RcFK72kbVHGD7eWKgQJBAMjj5I7tGMuvYZ2MXG2mHX4es90aAOlX5nHsINXH4IawVqONEF7wcs7V8bUQRaK2tni59TWow57MvvDwtjGcgckCQQCoK5vqcRbHJSxGNdXLHQJCcAXh4Ov4sbRdqug+GcPO8hEV9BjERrLFULypiwJWLd8bTd+2Uzj4ClyFVsCAf02zAkAFYSzusuS2F6U6jdavzQH/LZ1Nb3PUy9jM9jDO6MJXeQNo788fa7r3VP1bBuuGdvQd+YTaggFzEDKQyzFl1LYZAkAYIYnR7kBSeycLPBZdeuAkIGb3roqtuPIkrq18m73ZKCsDd29GWs60OY2Y1nWTYCmvhVEgnHiEPxhfmb8tsRa5AkAAr551x6wrL5kwLgkWt3itLx2JsEYEsX1Sf5SlGOLeVEC9AXgtsUYT3c1S5OZC+TnqIBjMZSR2c7CLENK00Aja";
      PrivateKey privateKey2 = loadPrivateKey(CaiJingPrivateKeyce);
        byte[] decryptByte = RSAUtils.decryptData(Base64.getDecoder().decode(GongyueToken),privateKey2 );
            String decryptStr = new String(decryptByte, "utf-8");
            if(!StringUtils.hasText(decryptStr)){
                log.info("密文错误，解密后的报文信息为空！");
            }
            String[] str = decryptStr.split(",");
            String appAccount = str[0];
            long startTime = Long.parseLong(str[1]);
        System.out.println(appAccount);
        System.out.println(startTime);
        System.out.println(System.currentTimeMillis());



        }




       // KeyPair keyPair = generateRSAKeyPair();
       // printPrivateKeyInfo(keyPair.getPrivate());
       // System.out.println("privateKeyString = " + getPrivateString(keyPair.getPrivate()));
        //printPublicKeyInfo(keyPair.getPublic());
      // System.out.println("publicKeyString = " + getPublicString(keyPair.getPublic()));
//        try{
//            String priviateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIGm0zqSIBzk6qZU1rLuRmwlOVTC7DVEqeyQEoKzlU+brLZ4iMhgZ/4pw09wzRFe8BYsvlPn4XCgPmD+X9GVlrUuL/MrJdqXi1eiZ153jRHAuWzB4rQep38D8QwQ/M4TkIRejamJjfX/E+BzwDJVb50hKUehdRP+GI+1B0RofbPzAgMBAAECgYAIb3o+zVMZjitWNx7g9W/PiXx5b1U/AgCsS5NovZZxX4eVN7JulxctCjqS6JvvDWZBixe3Ddc1JLfqR6KkWD894sa8UBN8NcFsNEPwvihqM81sqHoEoUW2iqneeBIZvp4f7V8IqGsuA9Ytg3ESJX3CpGSlJAtjDGEIIsKKV2ptwQJBAMPETq6OGLeVErcTUJBhOCVPtV5FVNp/EqsyAvRcR77XkNyIHfqgq2+CmwTzLkT5Vlen45KwL3md7UeSoLDfsNcCQQCpiuqujl/RsfLtPajp+8xiwotT2CR42apIZDDcO41+CT+qbeN8cfK5lQhWY/mVgsGdXFDN5ZuGorVpc9paogZFAkEAp8xl3LyzdvfyGLYXggmyxFh0Dx/CQZ5V9CNj4OwQGwZOhpitiZuaFnSOR0nhyQEm/u9K4vrdVpMFRSnrTnAN9QJAE03T5sgjNNcUF9sbbnjTDrOj9Yn4rSHrd46g2ZKWS7F15EvayQKYZnUHm/6KHnHi5a3rLWZlTPEdwsIELKmiGQJBALdY3wVmAiQTGPI9zRVX+6Bbo2UxHSwT5/szEm44A8mIVLYwc0cHIVzLyEb+Nkr4jSnhGJJJzSm3Fm59U2wOyF8=";
//            byte[] buffer = Base64.getDecoder().decode(priviateKey);
//            PrivateKey privateKey = getPrivateKey(buffer);
//            printPrivateKeyInfo(privateKey);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    //}

    /**
     * 用公钥加密 <br> 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param data 需加密数据的byte数据
     * @param publicKey 公钥
     * @return 加密后的byte型数据
     */
    public static byte[] encryptData(byte[] data, PublicKey publicKey) {

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 传入编码数据并返回编码结果
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return encryptedData;
        } catch (Exception e) {
            log.warn("公钥加密异常！e={}", e);
            return null;
        }
    }

    /**
     * 用私钥解密
     *
     * @param encryptedData 经过encryptedData()加密返回的byte数据
     * @param privateKey 私钥
     */
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            log.warn("私钥解密异常！e={}", e);
            return null;
        }
    }

    /**
     * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
     */
    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 通过私钥byte[]将公钥还原，适用于RSA算法
     */
    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 使用N、e值还原公钥
     */
    public static PublicKey getPublicKey(String modulus, String publicExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 使用N、d值还原私钥
     */
    public static PrivateKey getPrivateKey(String modulus, String privateExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.warn("字符串生成公钥异常，算法不匹配，可能是字符串有问题！e={}", e);
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            log.warn("字符串生成公钥异常，公钥非法！e={}", e);
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            log.warn("字符串生成公钥异常，公钥数据为空！e={}", e);
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从字符串中加载私钥<br> 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.warn("字符串生成私钥异常，算法不匹配，可能是字符串有问题！e={}", e);
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            log.warn("字符串生成私钥异常，私钥非法！e={}", e);
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            log.warn("字符串生成私钥异常，私钥数据为空！e={}", e);
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public static PublicKey loadPublicKey(InputStream in) throws Exception {
        try {
            return loadPublicKey(readKey(in));
        } catch (IOException e) {
            log.warn("公钥数据流读取错误！e={}", e);
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            log.warn("公钥输入流为空！e={}", e);
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从文件中加载私钥
     *
     * @param in 私钥文件名
     * @return 是否成功
     */
    public static PrivateKey loadPrivateKey(InputStream in) throws Exception {
        try {
            return loadPrivateKey(readKey(in));
        } catch (IOException e) {
            log.warn("私钥数据读取错误！e={}", e);
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            log.warn("私钥输入流为空！e={}", e);
            throw new Exception("私钥输入流为空");
        }
    }

    /**
     * 读取密钥信息
     */
    private static String readKey(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            sb.append(readLine);
        }

        return sb.toString();
    }

    /**
     * 打印公钥信息
     */
    public static void printPublicKeyInfo(PublicKey publicKey) {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        System.out.println("----------RSAPublicKey----------");
        System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());
        System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());
        System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());
        System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());
    }

    public static void printPrivateKeyInfo(PrivateKey privateKey) {
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        System.out.println("----------RSAPrivateKey ----------");
        System.out.println("Modulus.length=" + rsaPrivateKey.getModulus().bitLength());
        System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());
        System.out.println("PrivateExponent.length=" + rsaPrivateKey.getPrivateExponent().bitLength());
        System.out.println("PrivatecExponent=" + rsaPrivateKey.getPrivateExponent().toString());

    }

    public static String getPublicString(PublicKey rsaPublicKey){
        return new String(Base64.getEncoder().encode(rsaPublicKey.getEncoded()));
    }
    /**
     * 获取公钥
     * @return
     */
    public static PublicKey getPublicKey(){
        if(publicKey != null){
            return publicKey;
        }
        try{
           InputStream io = RSAUtils.class.getClassLoader().getResourceAsStream("pkcs8_public_key.pem");
            publicKey = RSAUtils.loadPublicKey(io);
        }catch (Exception e){
            log.error("ProxyLoginHelper#getPublicKey 获取公钥出错 error = {},cause = {},stack = {}",e.getMessage(),e.getCause(),e.getStackTrace());
        }
        System.out.println("测试"+"+"+publicKey);
        return publicKey;


    }

    public static String getPrivateString(PrivateKey rsaPrivateKey){
        return new String(Base64.getEncoder().encode(rsaPrivateKey.getEncoded()));
    }
}
