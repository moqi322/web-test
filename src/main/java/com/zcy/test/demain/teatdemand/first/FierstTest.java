package com.zcy.test.demain.teatdemand.first;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FierstTest {
    public static void main(String[] args) {
        ShouZiMuChuan.ChineseCharToEn ce = new ShouZiMuChuan.ChineseCharToEn();
        String allFirstLetter = ce.getAllFirstLetter("大毛衣来源收到收到收到").substring(0, 4) ;
        Calendar cal = Calendar.getInstance();

        System.out.println(allFirstLetter+"-"+cal.get(Calendar.YEAR));
        System.out.println(String.format("%09d",123));
       String docNo = ce.getAllFirstLetter(("大毛衣来源收到收到收到").substring(0, 4)) + "-" + cal.get(Calendar.YEAR) + "-" + String.format("%09d", 44);
//
//        System.out.println(docNo);
//        UniqId.getInstance().getUniqID();
//        System.out.println( UniqId.getInstance().getUniqID());
//        System.out.println(GenerateSequenceUtil.generateSequenceNo());

        //getId();

        System.out.println(String.format("%09d",433));

    }

    public  static String getId() {
        String id = "";
        //获取当前时间戳
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String temp = sf.format(new Date());
        //获取6位随机数
        int random = (int) ((Math.random() + 1) * 100000);
        id = temp + random;
        return id;

    }
}
