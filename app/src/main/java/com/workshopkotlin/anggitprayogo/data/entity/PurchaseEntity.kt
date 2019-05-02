package com.workshopkotlin.anggitprayogo.data.entity

import android.arch.persistence.room.*

@Entity(
    tableName = "purchase",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id_user"],
            childColumns = ["id_user"]
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id_produk"],
            childColumns = ["id_product"]
        )
    ],
    indices = [Index(value = ["id"], unique = true), Index(value = ["id_user"]), Index(value = ["id_product"])]
)
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = 0,
    @ColumnInfo(name = "id_user") var idUser: Long? = 0,
    @ColumnInfo(name = "id_product") var idProduct: Long? = null,
    @ColumnInfo(name = "shipping_address") var shippingAddress: String? = null
)