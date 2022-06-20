package com.jiaolin.jvm;

/**
 * @author johnny
 * @Classname StackFlowError
 * @Description  java.lang.StackOverflowError 栈溢出
 * @Date 2022/6/9 15:30
 */
public class StackFlowError {
    public static void main(String[] args) {
        stackErrorMethod();
    }

    private static void stackErrorMethod() {
        stackErrorMethod();
    }
}
