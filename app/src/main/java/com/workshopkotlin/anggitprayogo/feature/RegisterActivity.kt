package com.workshopkotlin.anggitprayogo.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.workshopkotlin.anggitprayogo.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.excludeFromRecents
import org.jetbrains.anko.intentFor

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        onClickListener()
    }

    private fun onClickListener() {
        tv_login.setOnClickListener {
            startActivity(intentFor<LoginActivity>().excludeFromRecents())
        }
    }
}
