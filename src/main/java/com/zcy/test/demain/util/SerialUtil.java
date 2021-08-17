package com.zcy.test.demain.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SerialUtil {
    private static final int MAX_VALUE = 99999999;

    private static final String FORMAT = "yyyyMMdd";

    private static final Format DF = new SimpleDateFormat(FORMAT);

    private static final byte[] lock = new byte[0];

    private String prefix = null;

    private Date date = null;

    private int number = 1;

    private static Map<String, SerialUtil> map = new HashMap<String, SerialUtil>();

    public static void main(String [] args){

        System.out.println(SerialUtil.newInstance("BDCID", new Date()).toString());
        System.out.println(SerialUtil.newInstance("BDCID", new Date()).toString());
        System.out.println(SerialUtil.newInstance("BDCID", new Date()).toString());
        System.out.println(SerialUtil.newInstance("BDCID", new Date()).toString());


        System.out.println(SerialUtil.newInstance("BDID", new Date()).toString());
        System.out.println(SerialUtil.newInstance("BDID", new Date()).toString());
        System.out.println(SerialUtil.newInstance("BDID", new Date()).toString());
        System.out.println(SerialUtil.newInstance("BDID", new Date()).toString());
    }

    private SerialUtil(String prefix, Date date) {
        this.prefix = prefix;
        this.date = date;
    }

    public static SerialUtil newInstance(String prefix) {
        Date date = new Date();
        return newInstance(prefix, date);
    }

    public static SerialUtil newInstance(String prefix, Date date) {

        SerialUtil o = null;
        synchronized (lock) {

            String key = getKey(prefix, date);
            if (map.containsKey(key)) {
                o = map.get(key);
                int number = o.getNumber();
                if (number < MAX_VALUE) {
                    o.setNumber(number + 1);
                } else {
                    o.setNumber(1);
                }
            } else {

                o = new SerialUtil(prefix, date);
                map.put(key, o);
            }
        }
        return o;
    }

    private static String getKey(String prefix, Date date) {
        return prefix + format(date);
    }

    private static String format(Date date) {
        return DF.format(date);
    }

    public String toString() {
        prefix = "";
        return prefix + format(date) + String.format("%08d", number);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}


