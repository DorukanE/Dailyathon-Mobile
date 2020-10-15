package com.dorukaneskiceri.dailyathon.login_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        backToSignUpForgot.setOnClickListener {
            val intent = Intent(it.context,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        imageBackButtonPsw.setOnClickListener {
            val intent = Intent(it.context,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}