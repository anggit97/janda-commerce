package com.workshopkotlin.anggitprayogo.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users", indices = [Index(value = ["email"], unique = true)])
@Parcelize
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_user") var id: Long = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "password") var password: String = ""
) : Parcelable