package com.jiaolin.refrence;

import java.lang.ref.WeakReference;

/**
 * @author johnny
 * @Classname WeakReferenceDemo
 * @Description 弱引用 只要gc一回收,不管什么原因,都会进行回收 周期比软软用更短
 * @Date 2022/6/15 09:52
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference weakReference = new WeakReference(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());

    }
}
