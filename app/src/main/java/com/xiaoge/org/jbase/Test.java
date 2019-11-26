package com.xiaoge.org.jbase;

public class Test {
    public void test() {
        Integer integer = Integer.parseInt("");

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello");
        threadLocal.get();

        Thread thread;
    }

    public static void test1() {
        Integer i1 = 11;
        Integer i2 = 11;

        Integer i3 = 250;
        Integer i4 = 250;

        Integer ii = new Integer(1);

        System.out.println("i1 == i2: " + (i1 == i2));
        System.out.println("i3 == i4: " + (i3 == i4));
    }

    public static void main(String args[]) {
        test1();
    }
}
