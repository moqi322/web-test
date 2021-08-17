package com.zcy.test.demain.lianxi;

import org.springframework.util.CollectionUtils;

import java.util.LinkedList;

public class IsEmpty {

    public static void main(String[] args) {
        //ListIsEmpty();
    }

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
