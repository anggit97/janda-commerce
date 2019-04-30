package com.workshopkotlin.anggitprayogo.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.workshopkotlin.anggitprayogo.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        onClickListener()
    }

    private fun onClickListener() {
        btn_login.setOnClickListener {
            startActivity<MainActivity>()
        }

        tv_register.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }
}
