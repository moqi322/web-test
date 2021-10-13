package com.zcy.test.demain.demand;

import io.terminus.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

@Slf4j
public class IsInclude {
    public static void main(String[] args) {
        String name = "张三丰";
        String myStr = "Runoob";
//        System.out.println(myStr.contains("Run"));
//        System.out.println(myStr.contains("o"));
//        System.out.println(myStr.contains("s"));

        String url="https://gxcgg-zhy.zhengcaiyun.cn/";

        String image = url.substring(url.lastIndexOf("//")+2);
        String substring = image.substring(0, 9);
        String s = substring.toUpperCase();
        System.out.println(s);
        System.out.println(substring);
        System.out.println(image);

        String[] split = url.split("//", 9);
        for (int i = 0; i < split.length; i++) {

//            System.out.println(split[i]);
        }


        if (url.contains("https://gxcgg") && url.contains("zhengcaiyun.cn")) {
//            System.out.println("------");
        }

//        if (name.contains("张")) {
//            System.out.println("包含");
////        }
//        long startTime = System.currentTimeMillis();
////        System.out.println(startTime);
//        System.out.println(new DateTime(startTime));
//        if(new DateTime(startTime).plusMinutes(5).isBeforeNow()){
//            log.info("无效加密串，加密时间已过期！");
////            System.out.println("过期");
//
//        }

//
//        String instanceCode = "aaa";
//        String aa = "GXCGG-zhy";
//        String bb = "PXWC";
//       if (StringUtils.isNotEmpty(instanceCode)){
//            System.out.println("888888");
//        }


    }
}
