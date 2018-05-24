package com.croquis.zigzag_shop_login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.croquis.zigzag_shop_login.data.LoginLocalDatasource
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.longToast

class LoginActivity : AppCompatActivity(), LoginContract.View, View.OnClickListener {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(toolbar)

        presenter = LoginPresenter()
        presenter.view = this
        presenter.loginDatasource = LoginLocalDatasource()

        rl_login.setOnClickListener(this)
    }

    override fun showErrorMessage(message: String) {
        longToast(message)
    }

    override fun showProgressSpin() {

    }

    override fun hideProgressSpin() {

    }

    override fun blurActivity() {

    }

    override fun unblurActivity() {

    }

    override fun showLoginStatus(loginMessage: String) {

    }

    override fun onClick(view: View?) {
        loading.visibility = View.VISIBLE
        tv_login.visibility = View.GONE

        presenter.login(id = et_user_id.text, password = et_user_pwd.text)
    }
}

















