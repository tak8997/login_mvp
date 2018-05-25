package com.croquis.zigzag_shop_login

import android.text.Editable
import com.croquis.zigzag_shop_login.data.LoginDatasource
import com.croquis.zigzag_shop_login.data.PreferencesHelper

/**
 * Created by Tak on 2018. 5. 24..
 */
interface LoginContract {

    interface View {

        fun showErrorMessage(message: String)

        fun showProgressSpin()
        fun hideProgressSpin()

        fun showLoginStatus(loginMessage: String)
        fun showLoginText()
        fun hideLoginText()

        fun showAutoLoginLayout()
        fun hideAutoLoginLayout()

        fun showAgreeChecked()
        fun showAgreeUnchecked()
    }


    interface Presenter {

        var view: View
        var loginDatasource: LoginDatasource
        var preferencesHelper: PreferencesHelper

        fun login(id: Editable, password: Editable, isAgree: Boolean)

        fun unsubsribe()

        fun checkAutoLogin()

        fun checkAgreeSelected(isSelected: Boolean)
    }
}