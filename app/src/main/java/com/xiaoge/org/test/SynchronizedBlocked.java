package com.xiaoge.org.test;

import java.util.concurrent.TimeUnit;

/**
 * 线程的中断操作对于正在等待获取的锁对象的synchronized方法或者代码块并不起作用，
 * 也就是对于synchronized来说，如果一个线程在等待锁，
 * 那么结果只有两种，要么它获得这把锁继续执行，要么它就保存等待，即使调用中断线程的方法，
 * 也不会生效。
 */
public class SynchronizedBlocked implements Runnable {

    public synchronized void f() {
        System.out.println("Trying to call f()");
        while (true)
            Thread.yield();
    }

    /**
     * 在构造器中创建新线程并启动获取对象锁
     */
    public SynchronizedBlocked() {
        //该线程已持有当前实例锁
        new Thread() {
            public void run() {
                f(); // Lock acquired by this thread
            }
        }.start();
    }

    public void run() {
        //中断判断
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("中断线程!!");
                break;
            } else {
                System.out.println("run f()!!");
                f();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlocked sync = new SynchronizedBlocked();
        Thread t = new Thread(sync);
        //启动后调用f()方法,无法获取当前实例锁处于等待状态
        t.start();
        TimeUnit.SECONDS.sleep(1);
        //中断线程,无法生效
        t.interrupt();
    }
}