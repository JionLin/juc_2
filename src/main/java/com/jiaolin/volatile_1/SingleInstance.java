package com.jiaolin.volatile_1;


/**
 * @author johnny
 * @Classname SingleInstance
 * @Description 单例模式, dcl双重检索+volatile关键字 防止指令重排
 * 1. 单线程中,创建的时候,不是同一个对象
 * 2. 解决的办法,
 * 使用sync关键字
 * 使用dcl来进行 double check lock +volatile 关键字
 * volatile 关键字 禁止指令重排,按顺序进行执行
 * @Date 2022/4/4 09:53
 */
public class SingleInstance {

    private static volatile SingleInstance instance = null;

    private SingleInstance() {
        System.out.println(Thread.currentThread().getName() + "\t 执行了");
    }

    public static SingleInstance getInstance() {
        if (instance == null) {
            synchronized (SingleInstance.class) {
                if (instance == null) {
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 单线程中,构造函数只会执行一次。
//        System.out.println(SingleInstance.getInstance() == SingleInstance.getInstance());
//        System.out.println(SingleInstance.getInstance() == SingleInstance.getInstance());
//        System.out.println(SingleInstance.getInstance() == SingleInstance.getInstance());

        // 多线程中环境中,不是同一个对象
        for (int i = 0; i <= 10; i++) {
            new Thread(() -> {
                SingleInstance.getInstance();
            }, String.valueOf(i)).start();
        }


    }

}
