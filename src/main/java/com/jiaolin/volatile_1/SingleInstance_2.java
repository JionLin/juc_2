package com.jiaolin.volatile_1;

/**
 * @author johnny
 * @Classname SingleInstance_2
 * @Description 单例模式2 重新写一遍
 * @Date 2022/4/4 10:27
 */
public class SingleInstance_2 {

    private volatile static SingleInstance_2 instance=null;

    private SingleInstance_2(){
        System.out.println(Thread.currentThread().getName()+"\t 执行了");
    }

    public static SingleInstance_2 getInstance(){
        if (instance==null){
            synchronized (SingleInstance_2.class){
                if (instance==null){
                    instance=new SingleInstance_2();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                SingleInstance_2.getInstance();
            },String.valueOf(i)).start();

        }
    }
}
