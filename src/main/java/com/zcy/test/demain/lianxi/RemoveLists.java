package com.zcy.test.demain.lianxi;

import com.zcy.test.demain.parm.User;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class RemoveLists {
    public static void main(String[] args) {
        //基本数据类型  string intger 类型去重
        intList();
        //对象去重
//        removeUse();

        Long price= 0L;
//        System.out.println(  Math.toIntExact(price));



//        Map<Long, User> target = new HashMap<Long, User>();
//        if (CollectionUtils.isNotEmpty(list1) && CollectionUtils.isNotEmpty(list2)) {             //把list1放入Map target中，key取用户id
//            for (User tempUser : list1) {
//                target.put(tempUser.getId(), tempUser);
//            }
//            for (User user2 : list2) {
//                Long userId = user2.getId();
//                if (target.containsKey(userId)) {
//                    User temp = target.get(userId);
//                    // 用户id重复，以list2中的用户姓名为准
//                    temp.setName(user2.getName());
//                    target.put(userId, temp);
//                } else {
//                    target.put(userId, user2);
//                }
//            }
//        }
//        List<User> list = new ArrayList<User>(target.values());

//        List<User> listAll = new ArrayList<User>();
//        listAll.addAll(list1);
//        listAll.addAll(list2);
//        // 去重
//        listAll = new ArrayList<User>(new LinkedHashSet<>(listAll));
//        System.out.println("-----"+listAll);

       // System.out.println("对象数据类型的list合并并且去重结果====>:" + list);
    }

    private static void removeUse() {
        //list<user>去重 不能直接比较list中的对象 需要重写bean对象的 equals和hashcode方法 然后放入set集合去重
        List<User> userList = new ArrayList<>();
        userList.add(new User("zhangsan",233,10L));
        userList.add(new User("zhangsan",233,10L));
        userList.add(new User("zhangsan11",2336,105L));

        Set<User> users = new HashSet<>(userList);
        ArrayList<User> objects = new ArrayList<>(users);
        objects.forEach(System.out::println);
    }

    /**
     * 基础数据类型的list合并并且去重，业务场景比较简单
//     */
    private static void intList() {
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(1);
        list2.add(4);
        list2.add(7);
        list2.add(10);
        List<Integer> listAll = new ArrayList<Integer>();
        listAll.addAll(list1);
        listAll.addAll(list2);
        // 去重
        listAll = new ArrayList<Integer>(new LinkedHashSet<>(listAll));
        System.out.println("方法一：" + listAll);
        list1.removeAll(list2);
        list1.addAll(list2);
        System.out.println("方法二：" + list1);
        //基本数据类型 都可   还有一种方法  jdk8特性 stream的distinct 去重
        listAll.stream().distinct().forEach(System.out::println);
    }
}
