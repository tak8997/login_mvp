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

    init {
        instance = this

//        initMemoryLeakDetector()
    }

    private fun initMemoryLeakDetector() {
        if (LeakCanary.isInAnalyzerProcess(instance))
            return

        LeakCanary.install(this)
    }


}