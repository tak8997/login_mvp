package com.croquis.zigzag_shop_login.di

import com.croquis.zigzag_shop_login.data.AppPreferencesHelper
import com.croquis.zigzag_shop_login.data.LoginDatasource
import com.croquis.zigzag_shop_login.data.LoginLocalDatasource
import com.croquis.zigzag_shop_login.data.PreferencesHelper
import org.koin.dsl.module.module


val appModule = module {
    single { LoginLocalDatasource() as LoginDatasource }
    single { AppPreferencesHelper() as PreferencesHelper }
}