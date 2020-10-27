package com.dorukaneskiceri.dailyathon.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.login_signup.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences: SharedPreferences = getSharedPreferences("com.dorukaneskiceri.dailyathon", MODE_PRIVATE)
        openApp(sharedPreferences)
    }

    private fun openApp(sharedPreferences: SharedPreferences) {
        Handler().postDelayed({
            val userIsLogin = sharedPreferences.getBoolean("userIsLogin",false)
            if(userIsLogin){
                val intent = Intent(this, MainAppActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 1500)
    }
}