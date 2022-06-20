package com.jiaolin.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author johnny
 * @Classname AtomicReferenceDemo
 * @Description 原子引用 进行比较,如果主内存的值和工作内存的值 相同,就执行相对应操作,如果不同,那么就循环直到更新成功。
 * @Date 2022/4/4 13:29
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user=new User(18,"zs");
        User user1=new User(20,"ls");

        AtomicReference<User> atomicReference=new AtomicReference<>(user);
        boolean value = atomicReference.compareAndSet(user, user1);
        System.out.println("value: \t"+value);
        System.out.println(atomicReference.compareAndSet(user, user1)+"值为\t "+atomicReference.get());

    }
}

class User{
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
