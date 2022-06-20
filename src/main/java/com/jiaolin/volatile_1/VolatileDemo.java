package com.jiaolin.volatile_1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @author johnny
 * @Classname VoliteDemo
 * @Description 1. 是什么
 * volatile 是一个轻量级的同步机制
 * 2。能干嘛
 * 1.可见性
 * 2.不保证原子性  原子性指的是最终的数据是否保存完整。
 *  原理
 *      当多个线程请求的时候,某一个线程拿到值了,更新到主内存中,其余的线程还是属于挂起状态,没有及时的更新,还是原来的值,会出现写时数据丢失
 *  如何解决
 *      sync
 *      使用juc 下面的 AutomicInteger
 * 3.禁止指令重排
 *  多线程程序中,在编译指令有优化重排的效果,防止程序不按顺序进行执行,
 *  系统正常会进行优化进行,
 * <p>
 * 和JMM相关
 * JMM指的是java内存模型
 * 1。里面设计到工作内存和主内存,其中祝内存是共享的,这个是jmm模型内置的,
 * 工作内存为私有的,是jvm虚拟机创建出来的线程,工作流程是 从工作内存中拷贝主内存中的变量到工作内存中,
 * 然后在工作内存中进行变量的拷贝,在重新写入到主内存的情况,然后其余的线程会同步最新的变量值。
 * 他的作用有3个
 * 1。  保证可见性
 * 2。  保证原子性
 * 3。  有序执行
 * @Date 2022/4/3 09:44
 */
public class VolatileDemo {

    public static void main(String[] args) {

        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.atomicInteger);

    }

    // volatile 不保证原子性
    private static void AtomicDemo() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlus();
                }
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value:" + myData.number);
    }

    /**
     * @return void
     * @Author johnny
     * @Description 可见性演示
     * @Date 11:32 2022/4/3
     * @Param []
     **/
    private static void seeOkByData() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t coming");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add60();
            System.out.println(Thread.currentThread().getName() + "\t 的值" + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
        }
        System.out.println(Thread.currentThread().getName() + "\t 的值为" + myData.number);
    }
}

class MyData {
    volatile int number = 0;

    public void add60() {
        number = 60;
    }

    public void addPlus() {
        number++;
    }

    // 使用AtomicInteger 保证数据的原子性
    AtomicInteger atomicInteger=new AtomicInteger();
    public void addPlusPlus(){
        atomicInteger.getAndIncrement();

    }

}