package com.jiaolin.jvm;

/**
 * @author johnny
 * @Classname JVMParamType
 * @Description -XX:+ - PrintGCDetails boolean 类型
 *              -XX:metaspaceSize=128m
 *             查看当前运行程序的配置 jinfo flag 进程编号
 *             -xmx 堆内存最大值
 *             xms 堆内存初始化值
 *             查看默认初始化值
 *             johnny@Johnny-3 interview_season_2 % java -XX:+PrintFlagsInitial -version
 *              java -XX:+PrintCommandLineFlags -version  打印默认参数 并行GC
 * @Date 2022/6/14 18:03
 */
public class JVMParamType {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello-Gc");
        Thread.sleep(Long.MAX_VALUE);
    }
}
