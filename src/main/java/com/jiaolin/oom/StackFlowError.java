package com.jiaolin.oom;

/**
 * @author johnny
 * @Classname StackFlowError
 * @Description oom 之stackoverFlowEoor
 * @Date 2022/6/15 12:21
 */
public class StackFlowError {
    public static void main(String[] args) {
        recursive();
    }

    private static void recursive() {
        recursive();
    }

}
