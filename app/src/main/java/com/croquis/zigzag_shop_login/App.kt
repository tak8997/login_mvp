package com.croquis.zigzag_shop_login

import android.app.Application

/**
 * Created by Tak on 2018. 5. 24..
 */

internal class App : Application() {

    companion object {
        lateinit var instance: App
            private set

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}