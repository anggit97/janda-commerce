package com.workshopkotlin.anggitprayogo.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "produk")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_produk") val idProduk: Int,
    @ColumnInfo(name = "nama_produk") val namaProduk: String?,
    @ColumnInfo(name = "harga_produk") val hargaProduk: String?,
    @ColumnInfo(name = "stok_produk") val stokProduk: Int,
    @ColumnInfo(name = "id_user") val idUser: Int
)