package com.jiaolin.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author johnny
 * @Classname SpinLock
 * @Description 自旋锁 、
 * 含义 指的是获取锁的线程不会立即阻塞,而是会使用循环的方式获取锁,好处是减少切换上下文的消耗,坏处是循坏会进行cpu的消耗
 * 好处:循环比较直到成功为止,没有类似于await的阻塞
 * 坏处:多次循环会消耗cpu的资源。
 * 手写自旋锁
 * while 先判断条件,条件成立 就执行循环体
 * do while 先执行循环体,在判断条件,如果条件成立,就执行循环体 必须执行一次循环体
 * @Date 2022/4/5 10:52
 */
public class SpinLock {

    AtomicReference<Thread> reference = new AtomicReference<>();

    // 先获取锁
    public void myLock() {
        //获取当前线程
        Thread thread = Thread.currentThread();
        // 判断条件,如果成立,就执行循环体里面的东西
        System.out.println(Thread.currentThread().getName() + "\t come in");
        // 开始自旋 自旋锁期望值是null 更新值为thread,如果是null 更新为thread,如果不是 就进行自旋
//        boolean value = reference.compareAndSet(null, thread);
//        System.out.println("value\t"+value);
        while (!reference.compareAndSet(null, thread)) {
            System.out.println("====");
        }
    }

    // 在解锁
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoke unlock");
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnlock();
        }, "t1").start();


        // 主线程停留3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLock.myLock();
            spinLock.myUnlock();
        }, "t2").start();


    }
}
