package com.croquis.zigzag_shop_login

import android.text.Editable
import com.croquis.zigzag_shop_login.data.LoginDatasource
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

    private var disposables: CompositeDisposable = CompositeDisposable()

    private val loginSubject: PublishSubject<TempUser> = PublishSubject.create()

    init {
        initLogin()
    }

    private fun initLogin() {
        disposables.add(loginSubject
                .throttleFirst(1500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribe {
                    verifyUser(it.id, it.password)
                })
    }

    private fun verifyUser(id: Editable, password: Editable) {
        if (!isValidEmail(id.toString())) {
            view.showErrorMessage("아이디를 다시 입력해주세요.")
            return
        }

        if (!isValidPassword(password.toString())) {
            view.showErrorMessage("비밀번호를 4자리 이상 입력해주세요.")
            return
        }

        disposables.add(loginDatasource
                .getUser(User(id.toString(), password.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.showProgressSpin()
                    view.showLoginStatus("로그인 중입니다..")
                    view.blurActivity()
                }
                .filter { it ->
                    if (!it) {
                        view.showErrorMessage("아이디, 패스워드를 확인해주세요.")
                        view.unblurActivity()
                        view.hideProgressSpin()
                        view.showLoginText()
                        return@filter false
                    }
                    return@filter true
                }
                .subscribe {
                    view.showLoginStatus("로그인 성공!")
                })
    }

    private fun isValidEmail(email: String?)
            = !email.isNullOrEmpty()

    private fun isValidPassword(password: String?)      //true 가 되게해야함
            = !password.isNullOrEmpty() && password?.let { it.length > 3 } ?: false

    override fun login(id: Editable, password: Editable) {
        loginSubject.onNext(TempUser(id, password))
    }



    data class TempUser(val id: Editable, val password: Editable)
}

