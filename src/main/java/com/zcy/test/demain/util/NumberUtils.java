package com.zcy.test.demain.util;

/**
 * @author zfLiu on 2019/10/29
 */
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.regex.Pattern;

public class NumberUtils {
    private NumberUtils() {
        //noting to do
    }

    /**
     * 获取区间的随机数 [min,max]
     *
     * @param min 最小值
     * @param max 最大值
     */
    public static int randomInt(int min, int max) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 金额从分转换成元
     */
    public static BigDecimal fen2Yuan(long fen) {
        return new BigDecimal(fen).multiply(new BigDecimal("0.01"));
    }

    /**
     * 金额从分转换成元
     */
    public static BigDecimal fen2Yuan(String fen) {
        return new BigDecimal(fen).multiply(new BigDecimal("0.01"));
    }

    /**
     * 金额从元转换成分
     */
    public static long yuan2Fen(String yuanStr) {
        return yuan2Fen(Double.parseDouble(yuanStr));
    }

    /**
     * 金额从元转换成分
     */
    public static long yuan2Fen(double yuan) {
        return yuan2Fen(BigDecimal.valueOf(yuan));
    }

    /**
     * 金额从元转换成分
     */
    public static long yuan2Fen(BigDecimal yuan) {
        return yuan.multiply(new BigDecimal(100L)).longValue();
    }

    /**
     * 将数字转为多位数不足用0补齐
     * @param i 需要转变的数字
     * @param digit 位数
     * @return 字符串
     */
    public static String numDigit(int i, int digit) {
        return String.format("%0"+digit+"d",i);
    }

    /**
     * 校验字符串是否正整数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^[+]{0,1}(\\d+)$");
        return pattern.matcher(str).matches();
    }


    public static void main(String[] args){
        boolean numeric = isNumeric("100");
        System.out.print(numeric);
    }

}

