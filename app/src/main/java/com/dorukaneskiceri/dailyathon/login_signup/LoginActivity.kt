package com.dorukaneskiceri.dailyathon.login_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dorukaneskiceri.dailyathon.MainAppActivity
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(customToolbarLogin)

        customToolbarLogin.setNavigationOnClickListener {
            val intent = Intent(it.context,LoginSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        textViewForgotPsw.setOnClickListener{

        }

        textViewBackToSignup.setOnClickListener {
            val intent = Intent(it.context,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginIntoAppButton.setOnClickListener {
            val intent = Intent(it.context,MainAppActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}