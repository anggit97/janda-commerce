package com.workshopkotlin.anggitprayogo.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.workshopkotlin.anggitprayogo.data.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert
    fun insertProduct(produk: ProductEntity)

    @Query("SELECT * FROM produk")
    fun getAllProducts() : List<ProductEntity>
}