package com.jiaolin.refrence;

import java.lang.ref.SoftReference;

/**
 * @author johnny
 * @Classname SoftReference
 * @Description 软引用 内存够用就不回收 不够用就回收
 * @Date 2022/6/15 09:33
 */
public class SoftReferenceTest {
    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference softReference = new SoftReference(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }
    public static void softRef_Memory_NOT_Enough() {
        Object o1 = new Object();
        SoftReference softReference = new SoftReference(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("回收"+o1);
            System.out.println(softReference.get());
        }
    }


    public static void main(String[] args) {
//        softRef_Memory_Enough();
        softRef_Memory_NOT_Enough();
    }
}
