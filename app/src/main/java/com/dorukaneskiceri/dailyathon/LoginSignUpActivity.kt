package com.dorukaneskiceri.dailyathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_signup.*

class LoginSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)

        signUpButton.setOnClickListener {
            val intent = Intent(it.context,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}