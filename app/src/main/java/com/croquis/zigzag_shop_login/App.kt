package com.croquis.zigzag_shop_login

import android.app.Application
import com.croquis.zigzag_shop_login.di.appModule
import com.croquis.zigzag_shop_login.di.presenterModule
import org.koin.android.ext.android.startKoin

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

        startKoin(this, listOf(presenterModule, appModule))
    }

}