package com.xiaoge.org.test;

import android.os.Looper;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public abstract class TT {
    public static final String TAG = TT.class.getSimpleName();

    public final void sys() {

        MyTask myTask = null;
        myTask.execute();
        myTask.cancel(true);

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
        Collections.binarySearch(null, null);

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

        Looper.prepare();
        Looper.loop();

        HashMap hashMap = null;
        hashMap.put("", "");
        hashMap.get("");
        ConcurrentHashMap concurrentHashMap = null;
        concurrentHashMap.put("", "");


        String ss = null;

        Object object;

        Thread.dumpStack();

        // Objects.equals("","");

        String[] arrs = new String[]{"hello", "world"};
        List<String> list1 = Arrays.asList(arrs);
        list1.toArray(new String[0]);

        // Arrays.copyOf(null,0);

        ArrayList arrayList = new ArrayList();
        arrayList.add(null);

        Hashtable hashtable;
        HashMap hashMap1;

        LinkedHashMap<String, String> linkedHashMap = null;
        linkedHashMap.put("", "");

        HashSet hashSet1;
    }
}
