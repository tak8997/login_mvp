package com.croquis.zigzag_shop_login

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Tak on 2018. 5. 24..
 */

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initMemoryLeakDetector()
    }

    private fun initMemoryLeakDetector() {
        if (LeakCanary.isInAnalyzerProcess(this))
            return

        LeakCanary.install(this)
    }


}