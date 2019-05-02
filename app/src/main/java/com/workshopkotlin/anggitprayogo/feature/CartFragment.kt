package com.workshopkotlin.anggitprayogo.feature

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.workshopkotlin.anggitprayogo.R
import com.workshopkotlin.anggitprayogo.data.JandaDatabase
import com.workshopkotlin.anggitprayogo.data.entity.ProductUserPurchase
import com.workshopkotlin.anggitprayogo.data.entity.PurchaseEntity
import com.workshopkotlin.anggitprayogo.data.sharedpref.SharedprefUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CartFragment : Fragment() {

    private val database: JandaDatabase by lazy {
        JandaDatabase(this.activity!!)
    }

    private var cartList: MutableList<ProductUserPurchase> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCartProcess()
    }

    private fun getCartProcess() {
        GlobalScope.launch (Dispatchers.Main){
            val isSuccess = withContext(Dispatchers.Default) {
                getChartAsync()
            }
        }
    }

    private fun getChartAsync(): MutableList<ProductUserPurchase> {
        return SharedprefUtil.idUser?.let { database.purchaseDao().getAllUserPurchase(it) }!!
    }
}
