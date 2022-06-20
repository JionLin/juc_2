package com.jiaolin.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author johnny
 * @Classname CyclicBarrierDemo
 * @Description Cyclic Barrier 循环栅栏 当++ 为最后一个的时候 进行放行
 * 与countDownLatch 相反,countDownLatch 是做减法,
 * CyclicBarrier 是做加法, 让一组线程运行时进行阻塞,并+1,到达最后一个时 屏障放开。
 * @Date 2022/4/10 14:11
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception {
        // 集成7颗龙珠 召唤神龙
        CyclicBarrier barrier = new CyclicBarrier(7,()->{
            System.out.println("集成7龙珠");
        });

        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 拿到第" + finalI + "颗龙珠");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }


    }
}
