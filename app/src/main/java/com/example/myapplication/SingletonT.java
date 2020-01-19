package com.example.myapplication;

import android.content.Context;

public class SingletonT {
    private static SingletonT s;
    private Context c;
    private SingletonT(Context c) {
        this.c = c;
    }
    public static SingletonT getSingletonT(Context c) {
        if (s == null) {
            synchronized (SingletonT.class) {
                if (s == null) {
                    //这里需要使用ApplicationContext来避免Leak
                    //s = new SingletonT(c.getApplicationContext());
                    s = new SingletonT(c);
                }
            }
        }
        return s;
    }
}
