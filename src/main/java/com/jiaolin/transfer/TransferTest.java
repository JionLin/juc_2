package com.jiaolin.transfer;

/**
 * @author johnny
 * @Classname TransferTest
 * @Description 数据的引用值
 * 基本类型传递的是复印件,值不改变.
 * 引用类型拷贝的地址值,进行拷贝传值。
 * string 常量池不改变。新建了地址值。还是使用的main方法。
 * @Date 2022/4/4 20:05
 */
public class TransferTest {
    public void changeValue(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("xxx");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    public static void main(String[] args) {
        TransferTest test = new TransferTest();
        int age = 20;
        test.changeValue(age);

        System.out.println("age----->" + age);
        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName:\t" + person.getName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("string----\t" + str);

    }
}

class Person {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }
}
