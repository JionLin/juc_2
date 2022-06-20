package com.jiaolin.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author johnny
 * @Classname ReentrantReadWriteDemo
 * @Description 共享锁/独占锁
 * 独占锁的理解: 指该锁一次只能被同一个线程所独有
 * 共享锁的理解: 指该锁一次可以被多个锁所共享
 * 如果不加同步锁会出现获取值为null问题
 * 解决方案为添加读写锁
 * 读读-- 共享锁
 * 读写-- 独占锁
 * 写写-- 独占锁
 * @Date 2022/4/5 13:05
 */
public class ReentrantReadWriteDemo {
    public static void main(String[] args) {
        // 线程操作资源类
        MapDemo mapDemo = new MapDemo();
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                mapDemo.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }


        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                mapDemo.get(finalI + "");
            }, String.valueOf(i)).start();
        }

    }
}


class MapDemo {
    private volatile Map<String, Object> sMap = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // put 请求
    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t put key: " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sMap.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t put 结束" + key);
        } finally {
            lock.writeLock().unlock();
        }


    }

    //get 请求
    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get key: " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = sMap.get(key);
            System.out.println(Thread.currentThread().getName() + "\t get 结束" + value);
        } finally {
            lock.readLock().unlock();
        }

    }

}
