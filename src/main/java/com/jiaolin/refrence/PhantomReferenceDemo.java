package com.jiaolin.refrence;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author johnny
 * @Classname PhantomReferenceDemo
 * @Description 须引用 字面意思是指的形同虚设
 * 它就和没有任何引用一样,在任何时候都可能被垃圾回收器回收 虚引用必须和引用队列联合使用
 * @Date 2022/6/15 10:48
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference reference = new PhantomReference(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());

        o1 = null;
        System.gc();
        System.out.println("------------------");
        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());

    }
}
