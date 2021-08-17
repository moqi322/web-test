package com.zcy.test.demain.teatdemand;

import com.zcy.test.demain.util.NumberUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) {
        String str="33.33";
        Integer test = (int) NumberUtils.yuan2Fen(1.00);
        System.out.println(test);
        //System.out.println(str.length() - (str.indexOf(".") + 1));

       // JSONObject jsonObject1 = (JSONObject) JSON.toJSON(basicInformation);
//        Integer aa=10;
//        long bb=1000;
//        if (aa==10|bb==100){
//            System.out.println("没有错");
//        }else {
//            System.out.println("报错了");
//        }
//        System.out.println(  String.format("共%s期当前正支付第%s期,",aa,bb));

//        long startTime = System.currentTimeMillis();
//
//        System.out.println(  String.format("共%s期当前正支付第%s期,",aa,bb));
//
//        long supplierTime = System.currentTimeMillis();
//
//        log.info("**********处理xx所用时间{}ms*******", supplierTime - startTime);
//        User user = new User();
//        user.setAge(23);
//        user.setName("zhangsan");
//
//        User user1 = new User();
//        user1.setAge(34);
//        user1.setName("wangwu");
//
//        User user2 = new User();
//        user2.setAge(65);
//        user2.setName("牛逼");
//
//        List<User> users = Arrays.asList(user1, user2, user);
//        List<User> collect = users.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
//        System.out.println(collect.get(0));
//        System.out.println(collect.size());


    }
}
