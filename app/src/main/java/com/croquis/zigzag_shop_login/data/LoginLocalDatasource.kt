package com.croquis.zigzag_shop_login.data

import android.os.Handler
import android.os.Looper
import com.croquis.zigzag_shop_login.data.model.Shop

/**
 * Created by Tak on 2018. 5. 24..
 */
internal class LoginLocalDatasource : LoginDatasource {

    companion object {
        const val networkLatencyInMillis = 2500L
        val DUMMY_SHOP_DATA = mutableListOf(
                Shop("tak1111", "1111"), Shop("tak2222", "2222"),
                Shop("tak3333", "3333"), Shop("tak4444", "4444"),
                Shop("tak5555", "5555"), Shop("tak6666", "6666"),
                Shop("tak7777", "7777"), Shop("tak8888", "8888"),
                Shop("tak9999", "9999"), Shop("tak0000", "0000"))
    }

    private val handler = Handler(Looper.getMainLooper())

    override fun login(shop: Shop, callback: LoginDatasource.LoginCallback) {
        handler.postDelayed({
            DUMMY_SHOP_DATA.forEach {
                if (it.id == shop.id && it.password == shop.password) {
                    callback.onLoginAvailable(true)
                    return@postDelayed
                }
            }

            callback.onLoginAvailable(false)
        }, networkLatencyInMillis)
    }

}