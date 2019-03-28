package com.croquis.zigzag_shop_login

import android.text.Editable

/**
 * Created by Tak on 2018. 5. 24..
 */
internal interface LoginContract {

    interface View {

        fun showErrorMessage(message: String)

        fun showProgressSpin()
        fun hideProgressSpin()

        fun showLoginStatus(loginMessage: String)
        fun showLoginText()
        fun hideLoginText()

        fun disableUserInput()
        fun showTermsAgreeState()
        fun showTermsDisAgreeState()

        fun showTermsAgreeChecked()
        fun showTermsAgreeUnchecked()

        fun blurActivity()
        fun unblurActivity()
    }


    interface Presenter {

        fun login(id: Editable, password: Editable, isAgree: Boolean)

        fun checkAutoLogin()
        fun checkTermsAgreeSelected(termsAgreeSelected: Boolean)
    }
}