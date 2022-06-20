package com.jiaolin.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author johnny
 * @Classname ABADemo
 * @Description 解决aba的问题,提出了一个时间戳版本号比较
 * 在原先的期望值和修改值比较中,添加一个时间戳版本号比较,如果相同,则进行执行规定操作.如果不是,就不进行修改。
 * @Date 2022/4/4 13:53
 */
public class ABADemo {
    public static void main(String[] args) {


        AtomicInteger atomicInteger = new AtomicInteger(100);
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        }).start();


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("是否成功\t " +
                    atomicInteger.compareAndSet(100, 2019) + "获取的值为\t" + atomicInteger.get());
        }).start();


        System.out.println("=================================");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("============以下是解决ABA问题的方案=====================");


        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(200, 1);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"初始化版本\t" + reference.getStamp());
            reference.compareAndSet(200, 100, reference.getStamp(), reference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"第2次版本值\t" + reference.getStamp());
            reference.compareAndSet(100, 200, reference.getStamp(), reference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"第3次版本值\t" + reference.getStamp());
        }, "T3").start();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"初始化版本\t" + reference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean value = reference.compareAndSet(200, 1024, reference.getStamp(), reference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+"修改是否成功\t" + value + "\t获取的值为\t" + reference.getReference());

        }, "T4").start();


    }
}
