package com.xiaoge.org.jbase;

public class Test {
    public void test() {
        Integer integer = Integer.parseInt("");

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello");
        threadLocal.get();
    }
}
