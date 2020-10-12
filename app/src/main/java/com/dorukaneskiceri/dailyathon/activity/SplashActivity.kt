package com.dorukaneskiceri.dailyathon.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.login_signup.LoginSignUpActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, LoginSignUpActivity::class.java)
            startActivity(intent)
            finish()
        },1700)
    }
}