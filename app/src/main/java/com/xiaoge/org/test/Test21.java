package com.xiaoge.org.test;

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

    }
}
