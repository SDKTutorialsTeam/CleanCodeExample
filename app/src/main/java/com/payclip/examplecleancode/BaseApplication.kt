package com.payclip.examplecleancode

import android.app.Application

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

}