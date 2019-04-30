package com.workshopkotlin.anggitprayogo.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.workshopkotlin.anggitprayogo.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id_user=:idUser")
    fun getUser(idUser: Int) : UserEntity
}