package com.workshopkotlin.anggitprayogo.feature

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.workshopkotlin.anggitprayogo.Config
import com.workshopkotlin.anggitprayogo.R
import com.workshopkotlin.anggitprayogo.data.entity.ProductEntity
import com.workshopkotlin.anggitprayogo.data.entity.UserEntity
import kotlinx.android.synthetic.main.activity_detail_product.*
import org.jetbrains.anko.startActivity

class DetailProductActivity : AppCompatActivity() {

    private var items: ProductEntity? = null
    private var items2: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        extractIntentAndBindView()

        onClickListener()
    }

    private fun onClickListener() {
        iv_back.setOnClickListener {
            finish()
        }

        btn_checkout.setOnClickListener {
            startActivity<CheckoutActivity>(
                Config.ITEMS to items
            )
        }
    }

    private fun extractIntentAndBindView() {
        items = intent?.getParcelableExtra(Config.ITEMS)
        items2 = intent?.getParcelableExtra(Config.ITEMS2)
        bindView(items, items2)
    }

    @SuppressLint("SetTextI18n")
    private fun bindView(
        items: ProductEntity?,
        items2: UserEntity?
    ) {
        tv_product_title.text = items?.namaProduk
        tv_product_price.text = "Rp.${items?.hargaProduk}"
        tv_product_detail.text = items?.deskpripsiProduk
    }
}
