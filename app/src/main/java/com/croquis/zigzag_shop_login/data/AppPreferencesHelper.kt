package com.croquis.zigzag_shop_login.data

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.croquis.zigzag_shop_login.App

/**
 * Created by Tak on 2018. 5. 25..
 */
internal class AppPreferencesHelper : PreferencesHelper {

    private val prefs: SharedPreferences by lazy {
        App.instance.getSharedPreferences("pref", MODE_PRIVATE)
    }

    override var autoLoginTermsAgreed: Boolean
        set(value) {
            prefs.edit().putBoolean("auto_login", true).apply()
        }
        get() = prefs.getBoolean("auto_login", false)


    override fun clear() {
        prefs.edit().remove("auto_login").apply()
    }
}