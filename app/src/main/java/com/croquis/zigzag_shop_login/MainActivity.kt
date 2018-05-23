package com.croquis.zigzag_shop_login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        rl_login.setOnClickListener({
            loading.visibility = View.VISIBLE
            tv_login.visibility = View.GONE
        })
    }
}
