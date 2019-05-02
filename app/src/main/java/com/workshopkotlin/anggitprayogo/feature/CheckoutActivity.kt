package com.workshopkotlin.anggitprayogo.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import com.workshopkotlin.anggitprayogo.Config
import com.workshopkotlin.anggitprayogo.R
import com.workshopkotlin.anggitprayogo.data.JandaDatabase
import com.workshopkotlin.anggitprayogo.data.entity.ProductEntity
import com.workshopkotlin.anggitprayogo.data.entity.PurchaseEntity
import com.workshopkotlin.anggitprayogo.data.sharedpref.SharedprefUtil
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.anko.*
import java.lang.Exception

class CheckoutActivity : AppCompatActivity() {

    private var items: ProductEntity? = null

    private val database: JandaDatabase by lazy {
        JandaDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        extractItemsAndBindView()

        onClickListener()
    }

    private fun onClickListener() {
        btn_checkout.setOnClickListener {
            if (isValid()) {
                val purchase = PurchaseEntity(
                    SharedprefUtil.idUser,
                    items?.idProduk,
                    et_shipping_address.text.toString()
                )
                doProcessCheckout(purchase)
                items
                items
            }
        }
    }

    private fun doProcessCheckout(purchase: PurchaseEntity) {
        GlobalScope.launch(Dispatchers.Main) {
            val isSuccess = async {
                try {
                    var result = doCheckoutAsync(purchase)
                    return@async true
                } catch (e: Exception) {
                    e.printStackTrace()
                    return@async false
                }
            }.await()

            if (isSuccess) {
                toast("Berhasil checkout")
                startActivity(intentFor<MainActivity>().newTask().clearTask())
            } else {
                toast("Gagal checkout")
            }
        }
    }

    private fun doCheckoutAsync(purchase: PurchaseEntity): Long {
        return database.purchaseDao().doInsertPurchase(purchase)
    }

    private fun extractItemsAndBindView() {
        items = intent?.getParcelableExtra(Config.ITEMS)
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
