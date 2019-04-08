package com.croquis.zigzag_shop_login.di

import com.croquis.zigzag_shop_login.LoginContract
import com.croquis.zigzag_shop_login.LoginPresenter
import org.koin.dsl.module.module

val presenterModule = module {
    factory<LoginContract.Presenter> { (view: LoginContract.View) -> LoginPresenter(get(), get(), view) }
}