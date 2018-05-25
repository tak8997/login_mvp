package com.croquis.zigzag_shop_login.data

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.croquis.zigzag_shop_login.App

/**
 * Created by Tak on 2018. 5. 25..
 */
class AppPreferencesHelper : PreferencesHelper {

    private val prefs: SharedPreferences by lazy {
        App.instance.getSharedPreferences("pref", MODE_PRIVATE)
    }


    override var isAutoLogin: Boolean
        set(value) {
            prefs.edit().putBoolean("auto_login", true).commit()
        }
        get() = prefs.getBoolean("auto_login", false)


    override fun clear() {
        prefs.edit().remove("auto_login").commit()
    }
}