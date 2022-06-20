package com.jiaolin.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author johnny
 * @Classname LockDemo
 * @Description lock锁的区别
 * 公平锁/非公平锁
 * 公平锁:先来先到的原则,也就是队列。指多个线程按照申请锁的顺序来执行
 * 非公平锁:指多个线程获取锁的顺序,不是按照申请的顺序来执行的,可能后申请的会比前面申请的在前面。默认为非公平锁。
 * 公平锁和非公平锁的区别
 * 1。 公平锁指的是 在并发过程中,就是很公平,每个线程获取锁的时候,会去查看锁的状态,如果是第一个,就会占有锁,
 * 其余的线程会加入等待队列,等待线程运行完成,也就是FIFO
 * 2。 非公平锁:   非公平锁比较粗鲁,上来就尝试占有锁,如果占有失败,就会按照公平锁的方式来尝试。
 * <p>
 * 可重入锁/递归锁 :指的是同一线程在外层获取锁后,在内存函数也会自动的获取锁,作用是避免死锁。
 * sync/ReentrantLock 方式的形式。
 * 理解: 同一线程 在外出方法获取锁的时候,在方法内部会自动进行获取锁。
 * @Date 2022/4/4 20:06
 */
public class LockDemo {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        new Thread(phone2,"C").start();
        new Thread(phone2,"D").start();
    }

    // 指的是A获取锁后,B线程无法获取锁,等A发送邮件完成后,释放锁后,B线程才能够获取锁。
    // 指的是A线程获取外层锁sendSms后,会自动获取内存的锁sendEmail锁。
    private static void synchMethod(Phone phone) {
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        new Thread(() -> {
            phone.sendSms();
        }, "B").start();
    }
}

class Phone2 implements Runnable {



    @Override
    public void run() {
        get();
    }

    ReentrantLock lock = new ReentrantLock();

    private void get() {
        lock.lock();
        try {
            System.out.println("线程\t" + Thread.currentThread().getName() + " invoke get");
            set();
        } finally {
            lock.unlock();
        }

    }

    private void set() {
        lock.lock();
        try {
            System.out.println("线程\t" + Thread.currentThread().getName() + " invoke set");
        } finally {
            lock.unlock();
        }
    }
}


class Phone {

    public synchronized void sendSms() {
        System.out.println("线程\t" + Thread.currentThread().getName() + "\t 发送短信");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println("线程\t" + Thread.currentThread().getName() + "\t 发送邮件");
    }

}