package com.croquis.zigzag_shop_login.data

import com.croquis.zigzag_shop_login.data.model.User
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/**
 * Created by Tak on 2018. 5. 24..
 */
class LoginLocalDatasource : LoginDatasource {

    companion object {
        var DUMMY_USER_DAT: List<User>
                = mutableListOf(
                User("tak1111", "1111"), User("tak2222", "2222"),
                User("tak3333", "3333"), User("tak4444", "4444"),
                User("tak5555", "5555"), User("tak6666", "6666"),
                User("tak7777", "7777"), User("tak8888", "8888"),
                User("tak9999", "9999"), User("tak0000", "0000"))
    }


    override fun getUser(user: User): Flowable<Boolean> {
        return Flowable.fromIterable(DUMMY_USER_DAT)
                .delay(5, TimeUnit.SECONDS)
                .map { it ->
                    return@map it.id == user.id && it.password == user.password
                }




    }
}