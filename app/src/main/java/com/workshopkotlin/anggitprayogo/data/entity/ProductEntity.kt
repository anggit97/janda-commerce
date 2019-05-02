package com.workshopkotlin.anggitprayogo.data.entity

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "produk",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id_user"],
        childColumns = ["id_owner"],
        onDelete = CASCADE
    )],
    indices = [
        Index(value = ["id_owner"]),
        Index(value = ["id_produk"], unique = true)
    ]
)
@Parcelize
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_produk") var idProduk: Long = 0,
    @ColumnInfo(name = "nama_produk") var namaProduk: String = "",
    @ColumnInfo(name = "deskripsi_produk") var deskpripsiProduk: String = "",
    @ColumnInfo(name = "harga_produk") var hargaProduk: String = "",
    @ColumnInfo(name = "stok_produk") var stokProduk: Int = 0,
    @ColumnInfo(name = "id_owner") var idUser: Long = 0
) : Parcelable