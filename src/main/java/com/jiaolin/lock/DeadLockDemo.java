package com.jiaolin.lock;

/**
 * @author johnny
 * @Classname HoldLockThread
 * @Description
 * @Date 2022/6/2 14:51
 */
class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t持有" + lockA + ",想要获得" + lockB);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t持有" + lockB + ",想要获得" + lockA);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "A").start();
        new Thread(new HoldLockThread(lockB, lockA), "B").start();
    }
}