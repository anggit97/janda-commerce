package com.workshopkotlin.anggitprayogo.feature

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.workshopkotlin.anggitprayogo.R
import com.workshopkotlin.anggitprayogo.adapter.ProductAdapter
import com.workshopkotlin.anggitprayogo.data.JandaDatabase
import com.workshopkotlin.anggitprayogo.data.entity.ProductEntity
import com.workshopkotlin.anggitprayogo.data.sharedpref.SharedprefUtil
import com.workshopkotlin.anggitprayogo.utils.setGone
import com.workshopkotlin.anggitprayogo.utils.setVisible
import kotlinx.android.synthetic.main.base_empty_list_layout.*
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductFragment : Fragment() {

    private val database: JandaDatabase by lazy {
        JandaDatabase(this.activity!!)
    }

    private val adapter: ProductAdapter by lazy {
        ProductAdapter(productList)
    }

    private var productList: MutableList<ProductEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        getProductByUserId()
    }

    private fun initRecyclerView() {
        rv_product.layoutManager = GridLayoutManager(activity, 2)
        rv_product.adapter = adapter
    }

    private fun getProductByUserId() {
        productList.clear()
        val idUser = SharedprefUtil.idUser
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.Default) {
                idUser?.let {
                    database.productDao().getAllProductsByIdUser(it)
                }
            }

            if (result?.size == 0) {
                rv_product.setGone()
                rl_empty.setVisible()
            } else {
                rv_product.setVisible()
                rl_empty.setGone()
                result?.let { productList.addAll(it) }
                adapter.notifyDataSetChanged()
            }
        }
    }
}