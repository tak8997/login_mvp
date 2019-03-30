package com.croquis.zigzag_shop_login.data

import com.croquis.zigzag_shop_login.data.model.Shop

/**
 * Created by Tak on 2018. 5. 24..
 */
interface LoginDatasource {

    interface LoginCallback {
        fun onLoginAvailable(loginAvailable: Boolean)
    }

    fun login(shop: Shop, callback: LoginCallback)
}