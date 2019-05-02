package com.workshopkotlin.anggitprayogo.adapter

import com.workshopkotlin.anggitprayogo.data.entity.ProductEntity

interface ProductAdapterListener {

    fun onClickItemProduct(productEntity: ProductEntity)
}