package com.dorukaneskiceri.dailyathon.login_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.view_model.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: UserListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelSignUp: UserSignUpViewModel
    private lateinit var viewModelChangePassword: ChangePasswordViewModel
    private lateinit var viewModelTagList: TagListViewModel
    private lateinit var viewModelCategoryTag: CategoryTagViewModel
    private lateinit var viewModelFindUser: UserTagDeleteViewModel
    private lateinit var viewModelUserTag: UserTagListViewModel
    private lateinit var viewModelSurveyList: SurveyListViewModel
    private lateinit var viewModelUserSurveys: UserSurveyViewModel
    private lateinit var viewModelUserSurveysRead: UserSurveyReadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        viewModelSignUp = ViewModelProvider(this).get(UserSignUpViewModel::class.java)
        viewModelChangePassword = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)
        viewModelTagList = ViewModelProvider(this).get(TagListViewModel::class.java)
        viewModelCategoryTag = ViewModelProvider(this).get(CategoryTagViewModel::class.java)
        viewModelFindUser = ViewModelProvider(this).get(UserTagDeleteViewModel::class.java)
        viewModelUserTag = ViewModelProvider(this).get(UserTagListViewModel::class.java)
        viewModelSurveyList = ViewModelProvider(this).get(SurveyListViewModel::class.java)
        viewModelUserSurveys = ViewModelProvider(this).get(UserSurveyViewModel::class.java)
        viewModelUserSurveysRead = ViewModelProvider(this).get(UserSurveyReadViewModel::class.java)

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
            getUserSurveysRead()
            //getUserSurveys()
            //getSurveys()
            //getUserTags() **IS NOT WORKING**
            //userTagDelete()
            //getCategoryTag()
            //getTagList()
            //changePassword()
            //doUserLogin()
            //doSignUp()
            //fetchUserList()
//            val intent = Intent(it.context, MainAppActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }

    private fun getUserSurveysRead() {
        viewModelUserSurveysRead.getUserSurveyRead()
        viewModelUserSurveysRead.surveyread.observe(this, Observer { response ->
            println(response.status)
            println(response.message)
        })
    }

    private fun getUserSurveys() {
        viewModelUserSurveys.getUserTags()
        viewModelUserSurveys.userSurveyList.observe(this, Observer { response ->
            println(response.surveyID)
        })
    }

    private fun getSurveys() {
        viewModelSurveyList.getSurveys()
        viewModelSurveyList.surveyList.observe(this, Observer { response ->
            println(response.surveyID)
            println(response.surveyName)
            println(response.surveyVisible)
        })
    }

    private fun getUserTags() {
        viewModelUserTag.getUserTags()
        viewModelUserTag.userTagList.observe(this, Observer { response ->
            println(response.tagID)
            println(response.tagName)
            println(response.categoryID)
            println(response.categoryName)
        })
    }

    private fun userTagDelete() {
        viewModelFindUser.getUserList()
        viewModelFindUser.findUser.observe(this, Observer { response ->
            println(response.message)
        })
    }

    private fun getCategoryTag() {
        viewModelCategoryTag.getCategoryTag()
        viewModelCategoryTag.categoryTagViewModel.observe(this, Observer { response ->
            println(response.tagID)
            println(response.tagName)
            println(response.categoryName)
        })
    }

    private fun getTagList() {
        viewModelTagList.getTagList()
        viewModelTagList.tagListViewModel.observe(this, Observer { response ->
            println(response.tagID)
            println(response.tagName)
            println(response.categoryID)
        })
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