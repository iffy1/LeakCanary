package com.example.myapplication

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var wv: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        wv = findViewById(R.id.holder)
        val settings = wv.settings
        settings.javaScriptEnabled = true
        settings.blockNetworkImage = false
        settings.domStorageEnabled = true
        wv.webViewClient = WebViewClient()
        produceLeak()

        fab.setOnClickListener { view ->
            produceLeak()
        }
    }

    fun produceLeak() {
        //AsyncTask持有Activity的引用，导致Leak
        MyTask().execute()
        //单例持有ActivityContext导致Leak,应该使用application context
        val s = SingletonT.getSingletonT(this)
        wv.loadUrl("https://www.163.com")
    }

    //内部类默认持有外部类的引用，会导致leak
    inner class MyTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void {
            while (true) {
                Thread.sleep(1000)
                println("iiiiiiiiii")
            }
        }
    }
}
