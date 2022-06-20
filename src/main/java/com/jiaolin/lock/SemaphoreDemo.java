package com.jiaolin.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author johnny
 * @Classname SemaphoreDemo 信号灯
 * @Description 指的是线程 走一个其余的才可以进来
 * 1. 控制并发数量
 * 2. 进行共享资源的互斥
 *
 * semaphore 两个方法,获取锁acquire 以及release释放锁
 * 需求
 * 我们模拟一个抢车位的场景，假设一共有6个车，3个停车位 抢到车位停3秒中,在出去
 * @Date 2022/4/10 14:15
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3,false);

        for (int i = 1; i <=10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 获取停车位");
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(Thread.currentThread().getName()+"\t 离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();

        }
    }
}
