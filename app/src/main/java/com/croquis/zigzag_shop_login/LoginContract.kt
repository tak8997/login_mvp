package com.croquis.zigzag_shop_login

import android.text.Editable
import com.croquis.zigzag_shop_login.data.LoginDatasource

/**
 * Created by Tak on 2018. 5. 24..
 */
interface LoginContract {

    interface View {
        fun showErrorMessage(message: String)

        fun showProgressSpin()
        fun hideProgressSpin()

        fun blurActivity()
        fun unblurActivity()

        fun showLoginStatus(loginMessage: String)
        fun showLoginText()


    }


    interface Presenter {

        var view: View
        var loginDatasource: LoginDatasource

        fun login(id: Editable, password: Editable)
    }
}