package com.dorukaneskiceri.dailyathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setSupportActionBar(customToolbar)

        customToolbar.setNavigationOnClickListener {
            val intent = Intent(it.context,LoginSignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}