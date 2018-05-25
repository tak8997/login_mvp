package com.croquis.zigzag_shop_login

import android.text.Editable
import android.util.Log
import com.croquis.zigzag_shop_login.data.LoginDatasource
import com.croquis.zigzag_shop_login.data.PreferencesHelper
import com.croquis.zigzag_shop_login.data.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Created by Tak on 2018. 5. 24..
 */
class LoginPresenter : LoginContract.Presenter {

    override lateinit var view: LoginContract.View

    override lateinit var loginDatasource: LoginDatasource

    override lateinit var preferencesHelper: PreferencesHelper

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val loginSubject: PublishSubject<TempUser> = PublishSubject.create()
    private val isAutoLogin: PublishSubject<Boolean> = PublishSubject.create()
    private val autoLoginSelected: PublishSubject<Boolean> = PublishSubject.create()

    init {
        initLogin()
    }

    private fun initLogin() {
        disposables.add(loginSubject
                .throttleFirst(1500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribe {
                    verifyUser(it.id, it.password, it.isAgree)
                })

        disposables.add(isAutoLogin
                .subscribe { it ->
                    Log.d("isAutoLogin", it.toString() )
                    if (it)
                        view.showAutoLoginLayout()
                    else
                        view.hideAutoLoginLayout()
                })

        disposables.add(autoLoginSelected
                .subscribe { it ->
                    if (it)
                        view.showAgreeUnchecked()
                    else
                        view.showAgreeChecked()
                })
    }

    private fun verifyUser(id: Editable, password: Editable, isAgree: Boolean) {
        if (!isValidEmail(id.toString())) {
            view.showErrorMessage("아이디를 다시 입력해주세요.")
            return
        }

        if (!isValidPassword(password.toString())) {
            view.showErrorMessage("비밀번호를 4자리 이상 입력해주세요.")
            return
        }

        if (!isAgree) {
            view.showErrorMessage("자동 로그인 약관에 동의해주세요.")
            return
        }

        disposables.add(loginDatasource
                .getUser(User(id.toString(), password.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it ->
                    if (!it) {
                        view.showLoginStatus(App.instance.getString(R.string.login_intro_0))
                        view.showErrorMessage("아이디, 패스워드를 확인해주세요.")

                        view.hideProgressSpin()
                        view.showLoginText()

                        return@filter false
                    }
                    return@filter true
                }
                .doOnSubscribe {
                    view.showLoginStatus("로그인 중입니다..")
                    view.showProgressSpin()
                    view.hideLoginText()
                }
                .subscribe {
                    preferencesHelper.isAutoLogin = true

                    view.showLoginStatus("로그인 성공!")
                })
    }

    private fun isValidEmail(email: String?)
            = !email.isNullOrEmpty()

    private fun isValidPassword(password: String?)
            = !password.isNullOrEmpty() && password?.let { it.length > 3 } ?: false


    override fun checkAgreeSelected(isSelected: Boolean) {
        autoLoginSelected.onNext(isSelected)
    }

    override fun login(id: Editable, password: Editable, isAgree: Boolean) {
        loginSubject.onNext(TempUser(id, password, isAgree))
    }

    override fun checkAutoLogin() {
        Log.d("isAutoLogin111", preferencesHelper.isAutoLogin.toString())
        isAutoLogin.onNext(preferencesHelper.isAutoLogin)
    }


    override fun unsubsribe() {
        disposables.clear()
    }



    data class TempUser(val id: Editable, val password: Editable, val isAgree: Boolean)
}

