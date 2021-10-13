package com.zcy.test.demain.lianxi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zcy.test.demain.demand.AccessAppKeyResult;
import com.zcy.test.demain.demand.DealtWithResult;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;

public class IsEmpty {


    public static void main(String[] args) {

        String aa="1000";

       String aa1 = "{\"syscode\":\"zcy\",\"operResult\":\"0\",\"dataType\":\"WfData\",\"operType\":\"Check\",\"message\":\"流程数据【】检测接收人不存在\"}";
        DealtWithResult dealtWithResult = JSON.parseObject(aa1, DealtWithResult.class);
//        System.out.println(dealtWithResult.getMessage());
////        {"syscode":"zcy","operResult":"0","dataType":"WfData","operType":"Check","message":"流程数据【】检测接收人不存在"}
//
//        System.out.println(Long.parseLong(aa));
//        System.out.println(Long.valueOf(aa));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"")
                .append("syscode\":\"")
                .append("1122").append("\",\"")
                .append("flowid\":\"").append("1122").append("\",\"")
                .append("requestname\":\"")
                .append("1122").append("\",\"")
                .append("workflowname\":\"")
                .append("1122").append("\",\"")
                .append("nodename\":\"")
                .append("1122").append("\",\"")
                .append("pcurl\":\"")
                .append("1122").append("\",\"")
                .append("appurl\":\"")
                .append("1122").append("\",\"")
                .append("creator\":\"")
                .append("1122").append("\",\"")
                .append("createdatetime\":\"")
                .append("1122").append("\",\"")
                .append("receiver\":\"")
                .append("1122").append("\",\"")
                .append("receivedatetime\":\"")
                .append("1122").append("\",\"")
                .append("isremark\":\"")
                .append("1122").append("\",\"")
                .append("viewtype\":\"")
                .append("1122")
                .append("\"}");
//        System.out.println(stringBuilder.toString());
//        String username="user";// 账户
//        String password="1234";// 密码
//        AccessAppKeyResult oo = new AccessAppKeyResult();
//        oo.setAppKey("00");
//        oo.setPrivatekey("22");
//
//        JSONObject.toJSONString(oo);
//        System.out.println( DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));


//        System.out.println(JSONObject.toJSONString(oo));
//        System.out.println(Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes()));
//
//       String bb="";
//        String.format(bb, new Object[] {
//                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")});
//
//        System.out.println( String.format(bb, new Object[] {
//                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")}));
//        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(new Date().getTime());
//        System.out.println(new Date().toString());
//        int a=1;
//        AccessAppKeyResult accessAppKeyResult ;
//        if(a==1){
//            AccessAppKeyResult accessAppKeyResult1 = new AccessAppKeyResult();
//            accessAppKeyResult1.setAppKey("11");
//            accessAppKeyResult=accessAppKeyResult1;
//            System.out.println(accessAppKeyResult);
        }
        //ListIsEmpty();


    private static void ListIsEmpty() {
//        使用Collection.size() 来检测是否为空在逻辑上没有问题，但是使用Collection.isEmpty()
//    使得代码更易读，并且可以获得更好的性能；除此之外，任何Collection.isEmpty()
//    实现的时间复杂度都是O(1) ，不需要多次循环遍历，但是某些通过Collection.size()
//    方法实现的时间复杂度可能是O(n)。O(1)纬度减少循环次数
        LinkedList<Object> collection = new LinkedList<>();
        if (collection.isEmpty()){
            System.out.println("collection is empty.");
        }
        //检测是否为null 可以使用CollectionUtils.isEmpty()
        if (CollectionUtils.isEmpty(collection)){
            System.out.println("collection is null.");

        }
    }
}
