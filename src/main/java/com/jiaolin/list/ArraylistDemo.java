package com.jiaolin.list;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author johnny
 * @Classname ArraylistDemo
 * @Description list 不安全性
 * 举例说明java的异常
 * concurrentModificationException 并发修改异常
 * @Date 2022/4/4 14:58
 */
public class ArraylistDemo {
    public static void main(String[] args) {
        // 举例说明 集合不安全原因
//        List<String> list= new ArrayList<>();
//        CopyOnWriteArrayList<String> list= new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> list= new CopyOnWriteArraySet<>();
        ConcurrentHashMap<String, String> concurrentHashMap=new ConcurrentHashMap();

        concurrentHashMap.put("1","2");
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }
}
