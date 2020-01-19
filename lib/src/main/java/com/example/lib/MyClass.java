package com.example.lib;

import java.util.concurrent.Semaphore;

public class MyClass {
    static Semaphore sp = new Semaphore(5);//锁的数量

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new TestThread(i).start();
        }
    }

    public static class TestThread extends Thread {
        private int id;

        private TestThread(int i) {
            id = i;
        }

        @Override
        public void run() {
            try {
                sp.acquire();//申请锁
                System.out.println("Thread:" + id + "Start");
                Thread.sleep(1000 + id % 5 * 10);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread:" + id + "end");
            sp.release();//释放锁
        }
    }
}


