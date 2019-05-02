package com.workshopkotlin.anggitprayogo.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.workshopkotlin.anggitprayogo.data.entity.ProductUserPurchase
import com.workshopkotlin.anggitprayogo.data.entity.PurchaseEntity

@Dao
interface PurchaseDao {

    @Insert
    fun doInsertPurchase(entity: PurchaseEntity): Long

    @Query("SELECT * FROM purchase WHERE id_user=:idUser")
    fun getPurchaseByIdUser(idUser: Long) : MutableList<PurchaseEntity>

    @Query("SELECT *  FROM purchase a, produk b, users c WHERE a.id_user=c.id_user AND a.id_product=b.id_produk AND a.id_user=:idUser")
    fun getAllUserPurchase(idUser: Long) : MutableList<ProductUserPurchase>
}