package com.workshopkotlin.anggitprayogo.feature

import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import com.workshopkotlin.anggitprayogo.R
import com.workshopkotlin.anggitprayogo.data.JandaDatabase
import com.workshopkotlin.anggitprayogo.data.entity.UserEntity
import com.workshopkotlin.anggitprayogo.data.sharedpref.SharedprefUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.*

class LoginActivity : AppCompatActivity() {

    private val database: JandaDatabase by lazy {
        JandaDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        onClickListener()
    }

    private fun onClickListener() {
        btn_login.setOnClickListener {
            if (isValid()) {
                val user = UserEntity(email = et_email.text.toString(), password = et_password.text.toString())
                doLoginProcess(user)
            }
        }

        tv_register.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }

    private fun doLoginProcess(user: UserEntity) {
        GlobalScope.launch(Dispatchers.Main) {
            val isSuccess = withContext(Dispatchers.Default) {
                try {
                    val result = doLoginAsync(user)
                    if (result.size > 0){
                        storeToSharedPref(result[0])
                        return@withContext true
                    }else{
                        return@withContext false
                    }
                } catch (e: SQLiteException) {
                    e.printStackTrace()
                    return@withContext false
                }
            }

            if (isSuccess) {
                toast("Berhasil login")
                startActivity(intentFor<MainActivity>().newTask().clearTask())
            } else {
                toast("Email dan password tidak sesuai")
            }
        }
    }

    private fun storeToSharedPref(result: UserEntity) {
        SharedprefUtil.isLoggin = true
        SharedprefUtil.nameUser = result.name
        SharedprefUtil.emailUser = result.email
        SharedprefUtil.idUser = result.id
    }

    private fun doLoginAsync(user: UserEntity): MutableList<UserEntity> {
        return database.userDao().doLogin(user.email!!, user.password!!)
    }

    private fun isValid(): Boolean {
        var valid = true

        et_email.validator()
            .nonEmpty()
            .validEmail()
            .addErrorCallback {
                et_email.error = it
                et_email.requestFocus()
                valid = false
            }
            .check()

        et_password.validator()
            .nonEmpty()
            .addErrorCallback {
                et_password.error = it
                et_password.requestFocus()
                valid = false
            }
            .check()

        return valid
    }
}
