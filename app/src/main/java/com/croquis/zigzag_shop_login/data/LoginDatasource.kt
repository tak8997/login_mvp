package com.croquis.zigzag_shop_login.data

import com.croquis.zigzag_shop_login.data.model.User
import io.reactivex.Flowable

/**
 * Created by Tak on 2018. 5. 24..
 */
interface LoginDatasource {
    fun getUser(user: User): Flowable<Boolean>

}