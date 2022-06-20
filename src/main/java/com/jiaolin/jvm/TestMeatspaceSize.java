package com.jiaolin.jvm;

/**
 * @author johnny
 * @Classname TestMeatspaceSize
 * @Description 进行配置, 来测试jdk8中元空间的大小
 * @Date 2022/6/9 14:56
 */
public class TestMeatspaceSize {
    public static void main(String[] args) {

        //查看CUP核数
        System.out.println(Runtime.getRuntime().availableProcessors());
//        返回Java虚拟机试图使用的最大内存量。 1/4
        long maxMemory = Runtime.getRuntime().maxMemory();
//返回Java虚拟机中的内存总量。 1/64
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.out.println("MAX_ MEMORY=" + maxMemory + " (字节)、" + (maxMemory / (double) 1024 / 1024) + "MB");
        System.out.println("TOTAL_ MEMORY = " + totalMemory + " (字节)、" + (totalMemory / (double) 1024 / 1024) + "MB");
    }
}
