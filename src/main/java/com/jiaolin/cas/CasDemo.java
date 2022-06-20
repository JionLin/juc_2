package com.jiaolin.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author johnny
 * @Classname CasDemo
 * @Description
 * cas 是 compare and swap cpu并发原语
 * 指的是主内存的值和工作内存中的值进行比较,如果相同就执行规定操作,如果不是,那么就一直进行比较,直到成功为止
 * 其中的底层涉及到 Unsafe类  unsafe.compareAndSwapInt(this, valueOffset, expect, update);
 * 其中的函数为 当前对象,以及当前对象的偏移量 和期望值 需要改变的值 Unsafe 类中的方法 都是native方法,调用的是底层的资源。
 * public final int getAndIncrement() {
 *         return unsafe.getAndAddInt(this, valueOffset, 1);
 *     }
 *      public final int getAndAddInt(Object var1, long var2, int var4) {
 *         int var5;
 *         do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 *
 *         return var5;
 *     }
 *     缺点
 *      1.开销比较大,一直在do while循环,当线程多的时候,比较次数比较多
 *      2。每次只能保证一个共享变量的原子操作
 *      3。引发aba问题。
 *          aba 问题下节写。
 *     为什么使用cas而不用sycn
 *     1。cas能够保证高并发,且能够保证数据的一致性。
 *     2。sync关键字是 加锁，同一时间只能是同一个请求过来,并发性下降
 * @Date 2022/4/4 10:50
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean value = atomicInteger.compareAndSet(5, 100);
        System.out.println("获取的值: " + value + " current data "+atomicInteger.get());

        boolean value2 = atomicInteger.compareAndSet(10, 200);
        System.out.println("获取的值: " + value2 + " current data "+atomicInteger.get());


    }
}
