package com.workshopkotlin.anggitprayogo.feature

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.workshopkotlin.anggitprayogo.R
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        runSplashScreen()
    }

    private fun runSplashScreen() {
        Handler().postDelayed({
            startActivity(intentFor<LoginActivity>().clearTask().clearTop())
        }, 2000)
    }
}
