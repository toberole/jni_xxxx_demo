package com.xiaoge.org.test;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test21 {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<String, String>(4, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Entry<String, String> eldest) {
                System.out.println("eldest k: " + eldest.getKey() + " v: " + eldest.getValue());
                return super.removeEldestEntry(eldest);
            }
        };

        map.put("1", "1");
        map.put("2", "2");
        map.get("1");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");

        SparseArray sparseArray = new SparseArray();
        sparseArray.append(1, "");
        sparseArray.delete(1);
        sparseArray.get(1);
        sparseArray.put(1, "");
        sparseArray.remove(1);

        Map<Integer, String> map1 = new HashMap<Integer, String>();


    }
}
