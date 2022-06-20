package com.jiaolin.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author johnny
 * @Classname CountDownLatchDemo
 * @Description 倒计时计数器 每次运行一次,数量减1 直到为0 就进行放行
 * 让一些线程阻塞,直到另外一些线程完成一系列操作后被唤醒。
 * 调用await 方法会被阻塞,调用countDown方法会计数减一
 *
 *  现在有这样一个场景，假设一个自习室里有7个人，其中有一个是班长，班长的主要职责就是在其它6个同学走了后，
 *  关灯，锁教室门，然后走人，
 *  因此班长是需要最后一个走的，那么有什么方法能够控制班长这个线程是最后一个执行，而其它线程是随机执行的
 * @Date 2022/4/10 14:10
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception{

        CountDownLatch countDownLatch=new CountDownLatch(6);

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 下课,走人");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长下课关门走人");


    }
}
