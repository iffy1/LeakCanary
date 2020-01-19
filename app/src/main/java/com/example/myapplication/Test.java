package com.example.myapplication;

import java.util.concurrent.Semaphore;

public class Test {
    static Semaphore sp = new Semaphore(5);

    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            new TestThread(i).start();
        }
    }

    public static class TestThread extends Thread {

        private int id = 0;

        public TestThread(int i) {
            id = i;
        }

        @Override
        public void run() {
            try {
                sp.acquire();
            } catch (Exception e) {

            }
            System.out.println("线程" + id + "开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }


            System.out.println("线程" + id + "运行结束");
            sp.release();
        }
    }
}
