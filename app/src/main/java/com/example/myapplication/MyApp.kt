package com.example.myapplication

import android.app.Application
//import com.squareup.leakcanary.LeakCanary
//import com.squareup.leakcanary.RefWatcher
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import com.squareup.leakcanary.LeakCanary
//import leakcanary.LeakCanary


class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
//    if (LeakCanary.isInAnalyzerProcess(this)) {
//        return
//    }
//    LeakCanary.install(this)

    }

}