package com.workshopkotlin.anggitprayogo.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "produk")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_produk") var idProduk: Long = 0,
    @ColumnInfo(name = "nama_produk") var namaProduk: String = "",
    @ColumnInfo(name = "deskripsi_produk") var deskpripsiProduk: String = "",
    @ColumnInfo(name = "harga_produk") var hargaProduk: String = "",
    @ColumnInfo(name = "stok_produk") var stokProduk: Int = 0,
    @ColumnInfo(name = "id_user") var idUser: Long = 0
)