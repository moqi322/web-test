package com.zcy.test.demain.testproxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.springframework.util.StringUtils;

import java.util.Iterator;


public class test01 {
    public static void main(String[] args) {
//        String a="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIrJN6r6SlFfe1JBY+yMU4sjwsVFmumrwMBwF9Q7o58HFtmcXmX4VPJmpgnCoi4y3E2UucpRdw/b8iF5EgzBu5OzEatdc62D8DFx3itbpfiMwgIhrEwlDzopVsCiNBtq9KpwgxMwuWUJP6dJaCmkKd4g9qU9LCYW/4R9lqRoUWSZAgMBAAECgYBQLNX/6/+q6qHvbt8u9BH/O3V1STSo8RO1GAxsCtgHMq08oVzXQ5KvRiV9VfcjATbLirOZ8V36x3ElbS/ENtcJLe5rIppJWBgsKOQQtpiKnZSQ6eFeu3kzADmxtcu2qEjnCCWL8E42/32tuo+5OjqwBAcFyat5OiDDcpus+BMgUQJBAMtCEr5wNboha1jNuQwxc5T0F16KLj/947nYuw3HxEuLQaQs6oZoBQW36LXu7fSnKSeSHWmEKbomLoOeT4b4BU8CQQCuzGypsABOODp5WZ/5i5v/aPzeAKfNRE31zZS+Om99+JnTzDECo4xwxMEW/k5OyoBnSo2cM4dfnxDgJWUOWs2XAkAop0qAdIkKdWy3Ek6Utb9cZ4XVDuY5Plqx7Ttcgjwsr+mtIJ63y05V2nbdDLDjYhvFImBnnluKM+DRynQ1lp3ZAkAGf/mGcdIBFpwuCQQm3mclnzzP8IxM2L5hZoyiaj/dGlJ2CsDDzVsakmZvuXMmAOe3b5FGfrAJATw6aYmReBTvAkAA4n76SwFp882zJLvKTo8w4IoUH4JvCzQb49Tdqaj7PQ8/dxr3eJ3/N8QHfTItl6httm7IZlKWwfQlSMv8a13A";
//      String b="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIrJN6r6SlFfe1JBY+yMU4sjwsVFmumrwMBwF9Q7o58HFtmcXmX4VPJmpgnCoi4y3E2UucpRdw/b8iF5EgzBu5OzEatdc62D8DFx3itbpfiMwgIhrEwlDzopVsCiNBtq9KpwgxMwuWUJP6dJaCmkKd4g9qU9LCYW/4R9lqRoUWSZAgMBAAECgYBQLNX/6/+q6qHvbt8u9BH/O3V1STSo8RO1GAxsCtgHMq08oVzXQ5KvRiV9VfcjATbLirOZ8V36x3ElbS/ENtcJLe5rIppJWBgsKOQQtpiKnZSQ6eFeu3kzADmxtcu2qEjnCCWL8E42/32tuo+5OjqwBAcFyat5OiDDcpus+BMgUQJBAMtCEr5wNboha1jNuQwxc5T0F16KLj/947nYuw3HxEuLQaQs6oZoBQW36LXu7fSnKSeSHWmEKbomLoOeT4b4BU8CQQCuzGypsABOODp5WZ/5i5v/aPzeAKfNRE31zZS+Om99+JnTzDECo4xwxMEW/k5OyoBnSo2cM4dfnxDgJWUOWs2XAkAop0qAdIkKdWy3Ek6Utb9cZ4XVDuY5Plqx7Ttcgjwsr+mtIJ63y05V2nbdDLDjYhvFImBnnluKM+DRynQ1lp3ZAkAGf/mGcdIBFpwuCQQm3mclnzzP8IxM2L5hZoyiaj/dGlJ2CsDDzVsakmZvuXMmAOe3b5FGfrAJATw6aYmReBTvAkAA4n76SwFp882zJLvKTo8w4IoUH4JvCzQb49Tdqaj7PQ8/dxr3eJ3/N8QHfTItl6httm7IZlKWwfQlSMv8a13A";
//        if (a.equals(b)){
//            System.out.println("一样的");
//        }



//        String str = "{\"userName\":\"mengHeng\",\"hoby\":\"写\"}";
        StringBuilder stringBuilder = new StringBuilder();
//        StringBuilder aa = stringBuilder.append("{\"")
////                .append("品牌\:")
//                .append(StringUtils.isEmpty("ff") ? "-": "ee").append("\",\"")
//                .append("型号").append("\":\"").append("gg").append("\",\"")
//                .append("aa").append("\":\"").append("bb").append("\":\"")
//                .append("cc").append("\":\"").append("dd")
//                .append("\"}");

//        StringBuilder ii = stringBuilder.append("{\"")
//                .append("品牌")
//                .append("\":\"")
//                .append(StringUtils.isEmpty("99") ? "-": "66").append("\",\"")
//                .append("型号\":\"").append("77");
//
//
//                ii.append("\"}");

//        net.sf.json.JSONObject jsonObj = net.sf.json.JSONObject.fromObject(str);
//        net.sf.json.JSONObject jsonObj1 = net.sf.json.JSONObject.fromObject(aa);
//        System.out.println(str);
//        System.out.println(ii);

//        System.out.println(jsonObj1);
//        System.out.println(jsonObj.toString());
// 循环遍历iterator

//        while(iterator.hasNext()){
//            String key = (String) iterator.next();
//
//            String value = jsonObj.getString(key);
//
//            System.out.println(key + " : " + value);
//
//        }
String  aa="22";
        stringBuilder.append("[");
         stringBuilder.append("{\"")
                .append("品牌\":\"")
                .append("aa").append("\",\"")
                .append("型号\":\"").append("00")
                .append("\"}");
         stringBuilder.append("]");
//        stringBuilder.Split('\n')
        System.out.println(stringBuilder);

//        JSONObject jo = new JSONObject(); // 对象{}
//
//        stringBuilder.append("{");
//        stringBuilder.append(""aa":"6",");
//        stringBuilder.append("}");

    }

}
