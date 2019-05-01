package com.workshopkotlin.anggitprayogo.feature

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import com.workshopkotlin.anggitprayogo.R
import com.workshopkotlin.anggitprayogo.data.JandaDatabase
import com.workshopkotlin.anggitprayogo.data.entity.UserEntity
import com.workshopkotlin.anggitprayogo.data.sharedpref.SharedprefUtil
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.*

class RegisterActivity : AppCompatActivity() {

    private val database: JandaDatabase by lazy {
        JandaDatabase.invoke(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        onClickListener()
    }

    private fun onClickListener() {
        btn_register.setOnClickListener {
            if (isValid()) {
                doRegisterProcess()
            }
        }

        tv_login.setOnClickListener {
            startActivity(intentFor<LoginActivity>().noHistory())
        }
    }

    private fun doRegisterProcess() {
        GlobalScope.launch(Dispatchers.Main) {
            val userEntity = UserEntity(name = et_name.text.toString(), email = et_email.text.toString())
            val isSuccess = withContext(Dispatchers.Default) {
                try {
                    doRegister(userEntity)
                    return@withContext true
                } catch (e: SQLiteConstraintException) {
                    return@withContext false
                }
            }

            if (isSuccess) {
                storeDataUserLocally()
                startActivity(intentFor<MainActivity>().newTask().clearTask())
                toast("Berhasil mendaftar")
            } else {
                toast("Email sudah digunakan, silahkan gunakan email lain")
            }
        }
    }

    private fun storeDataUserLocally() {
        SharedprefUtil.emailUser = et_email.text.toString()
        SharedprefUtil.nameUser = et_name.text.toString()
        SharedprefUtil.isLoggin = true
    }

    private fun doRegister(userEntity: UserEntity): Long {
        return database.userDao().insertUser(userEntity)
    }

    private fun isValid(): Boolean {
        var valid = true

        et_password.validator()
            .nonEmpty()
            .minLength(6)
            .addErrorCallback {
                et_password.error = it
                valid = false
                et_password.requestFocus()
            }
            .check()

        et_email.validator()
            .nonEmpty()
            .validEmail()
            .addErrorCallback {
                et_email.error = it
                valid = false
                et_email.requestFocus()
            }
            .check()

        et_name.validator()
            .nonEmpty()
            .minLength(3)
            .noNumbers()
            .addErrorCallback {
                et_name.error = it
                valid = false
                et_name.requestFocus()
            }
            .check()


        return valid
    }
}
