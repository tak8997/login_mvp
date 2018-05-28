package com.croquis.zigzag_shop_login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.croquis.zigzag_shop_login.data.AppPreferencesHelper
import com.croquis.zigzag_shop_login.data.LoginLocalDatasource
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast





class LoginActivity : AppCompatActivity(), LoginContract.View, View.OnClickListener {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        presenter = LoginPresenter()
        presenter.view = this

        presenter.loginDatasource = LoginLocalDatasource()
        presenter.preferencesHelper = AppPreferencesHelper()

        presenter.checkAutoLogin()

        rl_login.setOnClickListener(this)
        rl_autologin_agree.setOnClickListener(this)
    }

    private fun initView() {
        val userName = "꽃피는시절"
        tv_toolbar.text = "로그인(" + userName + ")"
        et_user_id.hint = userName + " 아이디"
        setSupportActionBar(toolbar)
    }

    override fun blurActivity() {
        body.alpha = 0.65F
    }

    override fun unblurActivity() {
        body.alpha = 1F
    }

    override fun showAutoLoginLayout() {
        rl_autologin_agree.visibility = View.GONE
        autologin_agree_success.visibility = View.VISIBLE
    }

    override fun hideAutoLoginLayout() {
        rl_autologin_agree.visibility = View.VISIBLE
        autologin_agree_success.visibility = View.GONE
    }


    override fun showErrorMessage(message: String) {
        toast(message)
    }

    override fun showProgressSpin() {
        progress_spin.show()
    }

    override fun hideProgressSpin() {
        progress_spin.hide()
    }

    override fun showLoginStatus(loginMessage: String) {
        tv_intro_0.text = loginMessage
        tv_intro_1.visibility = View.GONE
    }

    override fun showLoginText() {
        tv_intro_1.visibility = View.VISIBLE
        tv_login.visibility = View.VISIBLE
        tv_login.text = "로그인"
    }

    override fun hideLoginText() {
        tv_login.visibility = View.GONE
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.rl_autologin_agree, R.id.img_agreement_check ->
                presenter.checkAgreeSelected(rl_autologin_agree.isSelected)

            R.id.rl_login ->
                presenter.login(id = et_user_id.text, password = et_user_pwd.text, isAgree= rl_autologin_agree.isSelected)
        }
    }

    override fun showAgreeUnchecked() {
        rl_autologin_agree.isSelected = false
        img_agreement_check.setImageResource(R.drawable.agree_unchecked_selector)
    }

    override fun showAgreeChecked() {
        rl_autologin_agree.isSelected = true
        img_agreement_check.setImageResource(R.drawable.agree_check_selector)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubsribe()
    }
}




