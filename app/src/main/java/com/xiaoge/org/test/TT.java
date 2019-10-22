package com.xiaoge.org.test;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public abstract class TT {
    public static final String TAG = TT.class.getSimpleName();

    public final void sys() {
        HashSet hashSet = null;
        hashSet.add(new Object());

        String s = "abc";
        StringBuffer sb = new StringBuffer(s);
        sb = sb.reverse();

        StringBuilder sb1 = new StringBuilder(s);
        sb1 = sb1.reverse();

        s.toCharArray();

        Files files = null;

        Collection<String> collection;
        Collections.binarySearch(null,null);

        BlockingQueue<Object> bq = null;
        bq.poll();
        bq.remove();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Object> future = (Future<Object>) executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        Callable<String> callable = null;
        FutureTask futureTask = new FutureTask(callable);


        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("bbc");
        list.add("cbc");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
            if (str.equals("abc")) {
                it.remove();
            }
        }

    }
}
