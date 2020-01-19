package com.example.myapplication;

import android.animation.IntArrayEvaluator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.Semaphore;


public class MainActivityB extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MainActivityB:" + Thread.currentThread().getName());

        Handler mh = new MyHandler(this);


    }


    private static class MyHandler extends Handler {
        private WeakReference<MainActivityB> weakReference;

        public MyHandler(MainActivityB activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            System.out.println("---------------------------");
            System.out.println(msg.arg1);
        }
    }
}


