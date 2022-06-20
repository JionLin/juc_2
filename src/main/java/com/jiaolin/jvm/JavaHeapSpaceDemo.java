package com.jiaolin.jvm;

import java.util.Random;

/**
 * @author johnny
 * @Classname JavaHeapSpaceDemo
 * @Description 堆内存溢出
 * @Date 2022/6/9 17:32
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "johnny";
        while (true) {
            str += str + new Random().nextInt(111111111) + new Random().nextInt(222222222);
            str.intern();
        }
    }
}
