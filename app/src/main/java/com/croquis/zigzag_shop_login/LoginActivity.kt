package com.croquis.zigzag_shop_login

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.croquis.zigzag_shop_login.extension.getRoundedBitmap
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


internal class LoginActivity : AppCompatActivity(), LoginContract.View, View.OnClickListener {

    private val presenter: LoginContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupViews()
        setupListeners()

        presenter.checkAutoLogin()
    }

    override fun blurActivity() {
        blur_view.alpha = 0.38F
    }

    override fun unblurActivity() {
        blur_view.alpha = 0.0F
    }

    override fun disableUserInput() {
        edit_shop_id.inputType = 0
        edit_user_pwd.inputType = 0
        btn_login.isEnabled = false
    }

    override fun showTermsAgreeState() {
        group_auto_login_terms.visibility = View.GONE
        auto_login_succeed_terms.visibility = View.VISIBLE
    }

    override fun showTermsDisAgreeState() {
        group_auto_login_terms.visibility = View.VISIBLE
        auto_login_succeed_terms.visibility = View.GONE
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgressSpin() {
        progress_spin.alpha = 1.0F
        progress_spin.startAnimating()
    }

    override fun hideProgressSpin() {
        progress_spin.alpha = 0.0F
        progress_spin.stopAnimating()
    }

    override fun showLoginStatus(loginMessage: String) {
        tv_intro_0.text = loginMessage
        tv_intro_1.visibility = View.INVISIBLE
    }

    override fun showLoginText() {
        tv_intro_1.visibility = View.VISIBLE
        btn_login.visibility = View.VISIBLE
        btn_login.text = getString(R.string.login)
    }

    override fun hideLoginText() {
        btn_login.text = ""
    }

    override fun showTermsAgreeUnchecked() {
        img_agreement_check.isSelected = false
        img_agreement_check.setImageResource(R.drawable.agree_unchecked_selector)
    }

    override fun showTermsAgreeChecked() {
        img_agreement_check.isSelected = true
        img_agreement_check.setImageResource(R.drawable.agree_check_selector)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.img_agreement_check -> presenter.checkTermsAgreeSelected(img_agreement_check.isSelected)
            R.id.btn_login -> presenter.login(
                    id = edit_shop_id.text,
                    password = edit_user_pwd.text,
                    isAgree= img_agreement_check.isSelected)
        }
    }

    private fun setupListeners() {
        btn_login.setOnClickListener(this)
        img_agreement_check.setOnClickListener(this)
    }

    private fun setupViews() {
        val shopName = "꽃피는시절"

        setupToolbar(shopName)
        setupShopInfo(shopName)
        setupProgressStatus()
    }

    private fun setupShopInfo(shopName: String) {
        edit_shop_id.hint = "$shopName 아이디"

        val shopImageBitmap = BitmapFactory.decodeResource(resources, R.drawable.img_user)
        val shopImageRoundedBitmap = getRoundedBitmap(shopImageBitmap)
        shop.setImageBitmap(shopImageRoundedBitmap)
    }

    private fun setupToolbar(shopName: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            setDisplayShowTitleEnabled(false)
        }
        toolbar_title.text = String.format(getString(R.string.user_login), shopName)
    }

    private fun setupProgressStatus() {
        progress_spin.alpha = 0.0F
        progress_spin.stopAnimating()
    }
}




