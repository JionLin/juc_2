package com.jiaolin.refrence;

/**
 * @author johnny
 * @Classname StrongReference
 * @Description 强引用 Strong reference :特点是 打死不回收
 * @Date 2022/6/15 09:02
 */
public class StrongReference {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.gc();
        System.out.println(o2);
    }
}
