package com.example.lib;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTwo {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();//线程安全
        ai.getAndAdd(1);//代替i++

        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 50; i++) {
            Future<String> f = es.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return null;
                }
            });
            try {
                f.get();
            } catch (Exception e) {
                //
            }

            es.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("currentThreadName is:" + Thread.currentThread().getName());
                }
            });
        }
    }
}
