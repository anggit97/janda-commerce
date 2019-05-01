package com.workshopkotlin.anggitprayogo.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.workshopkotlin.anggitprayogo.R
import kotlinx.android.synthetic.main.app_bar_create_product.*

class CreateProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        onClickListener()
    }

    private fun onClickListener() {
        iv_logo.setOnClickListener {
            finish()
        }
    }
}
