package com.zcy.test.main;

import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudyRemove {
    public static void main(String[] args) {
       // ListAdd();
//        Enter();
//        extracted();
//        lamdba();
//        stringchange();

        stream();

    }

    // Scanner  自行输入
    private static void Enter() {
        Scanner sc = new Scanner(System.in);
        String str;
        while(true){
           str= sc.next();
           //str=str.replace("吗","");
           str=str.replace("?","");
          str=str.replace("十三水","");
            System.out.println(str);
        }
    }

    //list快速添加 和遍历 forEach
    private static void ListAdd() {
        List<Integer> list = new ArrayList<>();
        //使用能Collections工具类直接向已知集合添加元素 返回boolean值 true成功   或者是jdk9 list.of快速添加
        boolean b = Collections.addAll(list, 1, 2, 3, 4, 5, 7, 8, 9, 8, 7, 6, 7);

        ArrayList<String> newlist = Lists.newArrayList("张三", "李四", "王五");

        //2. 直接使用Arrays 的alist方法添加返回一个集合
        List<String> strings = Arrays.asList("zhangsan", "李四", "王武");

        //遍历集合1。
        list.forEach(System.out::println);
      //  list.forEach(System.out::print);//打印到一行
        //遍历集合2。
        strings.forEach(s -> System.out.println(s));

         // 遍历集合3 增强for循环
        for (String aa : newlist) {
            System.out.println(aa);
        }


    }

    //String转int
    private static void stringchange() {
        String str = "123";
        int i = Integer.parseInt(str);
        System.out.println(i);
    }

    /////  使用lambda表达式计算1-100的偶数和
    private static void lamdba() {
        long sum = IntStream.rangeClosed(1,100)
                .filter(a -> a % 2 == 0)
                .sum();
        System.out.println(sum);
    }

    //  使用lambda表达式计算1-100的偶数de数量
    private static void extracted() {
        long count = IntStream.rangeClosed(1, 100)
                .filter(a -> a % 2 == 0)
                .count();
        System.out.println(count);

    }

    //
    private static void stream(){
        List<Integer> list =  Arrays.asList(5,5,6,3,7,4,40,9,0,4);
        List<Integer> collect = list.stream().filter(a -> a !=3&&a==5).collect(Collectors.toList());

        collect.forEach(System.out::println);

    }

}
