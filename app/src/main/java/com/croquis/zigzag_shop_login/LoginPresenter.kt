package com.croquis.zigzag_shop_login

import android.text.Editable
import com.croquis.zigzag_shop_login.data.AppPreferencesHelper
import com.croquis.zigzag_shop_login.data.LoginDatasource
import com.croquis.zigzag_shop_login.data.model.User

/**
 * Created by Tak on 2018. 5. 24..
 */
internal class LoginPresenter(private val loginDataSource: LoginDatasource,
                              private val preferencesHelper: AppPreferencesHelper,
                              private val loginView: LoginContract.View) : LoginContract.Presenter {

    override fun checkTermsAgreeSelected(termsAgreeSelected: Boolean) {
        if (termsAgreeSelected) {
            loginView.showTermsAgreeUnchecked()
        } else {
            loginView.showTermsAgreeChecked()
        }
    }

    override fun login(id: Editable, password: Editable, isAgree: Boolean) {
         if (!verifyUser(id, password, isAgree)) {
             return
         }

        loginView.blurActivity()
        loginView.showLoginStatus(App.instance.getString(R.string.logging))
        loginView.showProgressSpin()
        loginView.hideLoginText()

        loginDataSource.login(User(id.toString(), password.toString()), object : LoginDatasource.LoginCallback {
            override fun onLoginAvailable(loginAvailable: Boolean) {
                if (loginAvailable) {
                    preferencesHelper.autoLoginTermsAgreed = true

                    loginView.showLoginStatus(App.instance.getString(R.string.login_success))
                    loginView.showLoginText()
                    loginView.hideProgressSpin()
                } else {
                    loginView.unblurActivity()
                    loginView.showLoginStatus(App.instance.getString(R.string.login_intro_0))
                    loginView.showErrorMessage(App.instance.getString(R.string.login_id_pwd_check))

                    loginView.hideProgressSpin()
                }
            }
        })
    }

    override fun checkAutoLogin() {
        if (preferencesHelper.autoLoginTermsAgreed) {
            loginView.disableUserInput()
            loginView.showTermsAgreeState()
        } else {
            loginView.showTermsDisAgreeState()
        }
    }

    private fun verifyUser(id: Editable, password: Editable, termsAgreed: Boolean): Boolean {
        if (!isValidEmail(id.toString())) {
            loginView.showErrorMessage(App.instance.getString(R.string.id_check))
            return false
        }

        if (!isValidPassword(password.toString())) {
            loginView.showErrorMessage(App.instance.getString(R.string.pwd_check))
            return false
        }

        if (!termsAgreed) {
            loginView.showErrorMessage(App.instance.getString(R.string.terms_agree_check))
            return false
        }

        return true
    }

    private fun isValidEmail(email: String?) = !email.isNullOrEmpty()

    private fun isValidPassword(password: String?) = !password.isNullOrEmpty() && password.let { it.length > 3 }

}

