package com.workshopkotlin.anggitprayogo.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.workshopkotlin.anggitprayogo.R
import com.workshopkotlin.anggitprayogo.data.sharedpref.SharedprefUtil
import org.jetbrains.anko.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        runSplashScreen()
    }

    private fun runSplashScreen() {
        Handler().postDelayed({
            checkIfUserAlreadyLogin()
        }, 2000)
    }

    private fun checkIfUserAlreadyLogin() {
        val isLoggin = SharedprefUtil.isLoggin ?: false
        if (isLoggin){
            startActivity(intentFor<MainActivity>().newTask().clearTask())
        }else{
            startActivity(intentFor<LoginActivity>().newTask().clearTask())
        }
    }
}
