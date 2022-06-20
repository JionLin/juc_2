package com.jiaolin.oom;

import java.nio.ByteBuffer;

/**
 * @author johnny
 * @Classname DirectBufferMemoryDemo
 * @Description Direct buffer memory 直接缓冲内存 由nio引起的 进行配置就行 、
 *-Xmx5M -Xmx5M -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5M
 * @Date 2022/6/15 12:44
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory" + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
