package com.example.myapplication

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.ContactsContract
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

    @TargetApi(26)
    fun produceLeak() {
        //AsyncTask持有Activity的引用，导致Leak
        MyTask().execute()

        //单例持有ActivityContext导致Leak,应该使用application context
        val s = SingletonT.getSingletonT(this)

        wv.loadUrl("https://www.163.com")

        val uri = ContactsContract.Contacts.CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null)



        //registerReceiver(MyBroadCast(),IntentFilter("com.iffy.action"))
    }

    inner class MyBroadCast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

        }

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
