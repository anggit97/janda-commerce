package com.workshopkotlin.anggitprayogo.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import com.workshopkotlin.anggitprayogo.R
import com.workshopkotlin.anggitprayogo.data.entity.ProductEntity
import kotlinx.android.synthetic.main.activity_checkout.*
import org.jetbrains.anko.toast

class CheckoutActivity : AppCompatActivity() {

    private var items: ProductEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        extractItemsAndBindView()

        onClickListener()
    }

    private fun onClickListener() {
        if (isValid()){
            toast("Berhasil checkout")
        }
    }

    private fun extractItemsAndBindView() {
        tv_product_summery.text = items?.namaProduk
            .plus(" - ")
            .plus("Rp. ${items?.hargaProduk}")

    }

    private fun isValid(): Boolean {
        var valid = true

        et_shipping_address.validator()
            .minLength(10)
            .addErrorCallback {
                et_shipping_address.error = it
                et_shipping_address.requestFocus()
                valid = false
            }
            .check()

        return valid
    }
}
