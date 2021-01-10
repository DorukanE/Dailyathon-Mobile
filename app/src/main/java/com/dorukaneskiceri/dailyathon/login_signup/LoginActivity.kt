package com.dorukaneskiceri.dailyathon.login_signup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.UserLoginModel
import com.dorukaneskiceri.dailyathon.view_model.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModelUserLogin: UserLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBar3.visibility = View.GONE

        val sharedPreferences: SharedPreferences = getSharedPreferences(
            "com.dorukaneskiceri.dailyathon",
            MODE_PRIVATE
        )

        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        setSupportActionBar(customToolbarLogin)

        textViewForgotPsw.setOnClickListener {
            val intent = Intent(it.context, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        textViewBackToSignup.setOnClickListener {
            val intent = Intent(it.context, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginIntoAppButton.setOnClickListener {
            doUserLogin(it, progressBar3, sharedPreferences)
        }
    }

    private fun doUserLogin(
        view: View,
        progressBar: ProgressBar,
        sharedPreferences: SharedPreferences
    ) {
        if(textInputEmailLogin.editText!!.text.isNotEmpty() && textInputPasswordLogin.editText!!.text.isNotEmpty()){
            progressBar3.visibility = View.VISIBLE
            val email = textInputEmailLogin.editText!!.text.trim().toString()
            val password = textInputPasswordLogin.editText!!.text.trim().toString()

            viewModelUserLogin.postUserLogin(email, password, view, progressBar, sharedPreferences)
            viewModelUserLogin.myUserLogin.observe(this, { response ->
                savePreferences(response)
                println(response.userInformation)
                println(response.userInformation.userName)
                println(response.userInformation.userMail)
                println(response.token)
            })

        }else{
               Snackbar.make(
                   view,
                   "Lütfen E-posta veya Şifrenizi kontrol ediniz",
                   Snackbar.LENGTH_SHORT
               ).show()
        }
    }

    private fun savePreferences(response: UserLoginModel) {
        val sharedPreferencesCity: SharedPreferences = getSharedPreferences(
            "userCity",
            MODE_PRIVATE
        )
        val sharedPreferencesMail: SharedPreferences = getSharedPreferences(
            "userEmail",
            MODE_PRIVATE
        )
        val sharedPreferencesPassword: SharedPreferences = getSharedPreferences(
            "userPassword",
            MODE_PRIVATE
        )
        val sharedPreferencesToken: SharedPreferences = getSharedPreferences(
            "userToken",
            MODE_PRIVATE
        )
        val sharedPreferencesUserID: SharedPreferences = getSharedPreferences(
            "userID",
            MODE_PRIVATE
        )
        val sharedPreferencesUserName: SharedPreferences = getSharedPreferences(
            "userName",
            MODE_PRIVATE
        )
        val sharedPreferencesUserSurname: SharedPreferences = getSharedPreferences(
            "userSurname",
            MODE_PRIVATE
        )

        val userName = response.userInformation.userName
        val userSurname = response.userInformation.userSurname
        val userEmail = response.userInformation.userMail
        val userPassword = response.userInformation.userPassword
        val userToken = response.token
        val userID = response.userInformation.userId
        val userCity = response.userInformation.userCity

        sharedPreferencesMail.edit().putString("email", userEmail).apply()
        sharedPreferencesPassword.edit().putString("password", userPassword).apply()
        sharedPreferencesToken.edit().putString("token", userToken).apply()
        sharedPreferencesUserID.edit().putInt("userID", userID).apply()
        sharedPreferencesUserName.edit().putString("name", userName).apply()
        sharedPreferencesUserSurname.edit().putString("surname", userSurname).apply()
        sharedPreferencesCity.edit().putString("city", userCity).apply()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }
}