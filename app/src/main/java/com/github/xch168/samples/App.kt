package com.github.xch168.samples

import android.app.Application

class App : Application() {

    companion object {
        lateinit var appContext: Application;
    }

    override fun onCreate() {
        super.onCreate()

        appContext = this;
    }
}