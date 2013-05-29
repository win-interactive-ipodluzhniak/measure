package com.test;

import com.google.common.cache.CacheBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class HashMeasure {

    public static void main(String... str) {
        //final Map map = new ConcurrentHashMap();
        final Map map = CacheBuilder.newBuilder().expireAfterAccess(20, TimeUnit.MILLISECONDS).build().asMap();
        for (int i = 0; i < 100000; i++) {
            map.put(i, "Start");
        }

        Thread t = new Thread(new Runnable() {
            public void run() {
                for (;;) {

                    map.put(map.size(), Math.random());

                }
            }
        });
        t.start();


        Measurable m = new MeasurableTask(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    map.get(Math.random() * map.size());
                }

            }
        });
        m.measure(10000);
        System.out.println(m);
    }
}
