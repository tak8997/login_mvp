package com.croquis.zigzag_shop_login.data

import android.os.Handler
import android.os.Looper
import com.croquis.zigzag_shop_login.data.model.User

/**
 * Created by Tak on 2018. 5. 24..
 */
internal class LoginLocalDatasource : LoginDatasource {

    companion object {
        val networkLatencyInMillis = 2500L
        val DUMMY_USER_DATA = mutableListOf(
                User("tak1111", "1111"), User("tak2222", "2222"),
                User("tak3333", "3333"), User("tak4444", "4444"),
                User("tak5555", "5555"), User("tak6666", "6666"),
                User("tak7777", "7777"), User("tak8888", "8888"),
                User("tak9999", "9999"), User("tak0000", "0000"))
    }

    private val handler = Handler(Looper.getMainLooper())

    override fun login(user: User, callback: LoginDatasource.LoginCallback) {
        handler.postDelayed({
            DUMMY_USER_DATA.forEach {
                if (it.id == user.id && it.password == user.password) {
                    callback.onLoginAvailable(true)
                    return@postDelayed
                }
            }

            callback.onLoginAvailable(false)
        }, networkLatencyInMillis)
    }

}