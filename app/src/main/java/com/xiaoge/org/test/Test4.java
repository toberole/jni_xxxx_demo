package com.xiaoge.org.test;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test4 {
    public static void main(String[] args) {
        ReferenceQueue referenceQueue = new ReferenceQueue();
        SoftReference<Object> softReference = new SoftReference<Object>(new Object(), referenceQueue);

        Object o1 = softReference.get();
        Object o2 = referenceQueue.poll();

        System.out.println(o1);
        System.out.println(o2);

        PhantomReference<Object> phantomReference = new PhantomReference<>(new Object(), new ReferenceQueue<>());

        SocketAddress socketAddress = new InetSocketAddress(8080);

        // FileChannel fileChannel = FileChannel.open()

//        Path path = Paths.get("");
//        Files files = Files.copy();

        // AsynchronousServerSocketChannel.open();

    }
}
