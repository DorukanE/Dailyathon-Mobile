package com.dorukaneskiceri.dailyathon.login_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.api_model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.view_model.ChangePasswordViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserSignUpViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: UserListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelSignUp: UserSignUpViewModel
    private lateinit var viewModelChangePassword: ChangePasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        viewModelSignUp = ViewModelProvider(this).get(UserSignUpViewModel::class.java)
        viewModelChangePassword = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)

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
            changePassword()
            //doUserLogin()
            //doSignUp()
            //fetchUserList()
//            val intent = Intent(it.context, MainAppActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }

    private fun changePassword() {
        viewModelChangePassword.changePassword()
        viewModelChangePassword.changePasswordField.observe(this, Observer { response ->
            println(response.message)
        })
    }

    private fun doSignUp() {
        viewModelSignUp.postUserSignUp()
        viewModelSignUp.myUserSignUp.observe(this, Observer { response ->
            println(response.message)
        })
    }

    private fun doUserLogin() {
        viewModelUserLogin.postUserLogin()
        viewModelUserLogin.myUserLogin.observe(this, Observer { response ->

            println(response.userInformation)
            println(response.userInformation.userName)
            println(response.userInformation.userMail)
            println(response.userInformation.userPassword)
            println(response.token)
        })

    }

    private fun fetchUserList() {
        viewModel.getUserList()
        viewModel.myUserList.observe(this, Observer { response ->
            println(response.userName)
            println(response.userCity)
        })

    }
}