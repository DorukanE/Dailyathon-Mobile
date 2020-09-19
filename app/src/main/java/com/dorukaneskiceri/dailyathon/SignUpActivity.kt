package com.dorukaneskiceri.dailyathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        buttonNameSurname.setOnClickListener {
            val text = textInputName.editText?.text
            println(text.toString())
        }

    }
}