package com.example.myapplication

import android.app.Application
import android.content.Context

class SmartCiryApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object{
        lateinit var context: Context
        lateinit var TOKEN:String
    }

}