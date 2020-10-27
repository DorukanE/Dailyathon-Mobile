package com.dorukaneskiceri.dailyathon.login_signup

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isNotEmpty
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.activity.MainAppActivity
import com.dorukaneskiceri.dailyathon.view_model.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: UserListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelSignUp: UserSignUpViewModel
    private lateinit var viewModelChangePassword: ChangePasswordViewModel
    private lateinit var viewModelTagList: TagListViewModel
    private lateinit var viewModelCategoryTag: CategoryTagViewModel
    private lateinit var viewModelFindUser: UserTagDeleteViewModel
    private lateinit var viewModelUserTag: UserTagListViewModel
    private lateinit var viewModelUserSurveyList: UserSurveyListViewModel
    private lateinit var viewModelUserSurveysRead: UserSurveyReadViewModel
    private lateinit var viewModelUserAnnouncementRead: UserAnnouncementReadViewModel
    private lateinit var viewModelNewsList: NewsListViewModel
    private lateinit var viewModelEntertainmentList: EntertainmentListViewModel
    private lateinit var viewModelSportList: SportListViewModel
    private lateinit var viewModelLeagueList: LeagueListViewModel
    private lateinit var viewModelUserTagSelect: UserTagSelectViewModel
    private lateinit var viewModelUserAnnouncements: UserAnnouncementListViewModel
    private lateinit var viewModelUserLeagues: UserLeagueListViewModel
    private lateinit var viewModelUserLeagueTableNames: UserLeagueTableNameViewModel
    private lateinit var viewModelUserNews: UserNewsListViewModel
    private lateinit var viewModelUserTagEntertainment: UserTagEntertainmentViewModel
    private lateinit var viewModelUserCityEntertainment: UserCityEntertainmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBar3.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        viewModelSignUp = ViewModelProvider(this).get(UserSignUpViewModel::class.java)
        viewModelChangePassword = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)
        viewModelTagList = ViewModelProvider(this).get(TagListViewModel::class.java)
        viewModelCategoryTag = ViewModelProvider(this).get(CategoryTagViewModel::class.java)
        viewModelFindUser = ViewModelProvider(this).get(UserTagDeleteViewModel::class.java)
        viewModelUserTag = ViewModelProvider(this).get(UserTagListViewModel::class.java)
        viewModelUserSurveyList = ViewModelProvider(this).get(UserSurveyListViewModel::class.java)
        viewModelUserSurveysRead = ViewModelProvider(this).get(UserSurveyReadViewModel::class.java)
        viewModelUserAnnouncementRead = ViewModelProvider(this).get(UserAnnouncementReadViewModel::class.java)
        viewModelNewsList = ViewModelProvider(this).get(NewsListViewModel::class.java)
        viewModelEntertainmentList = ViewModelProvider(this).get(EntertainmentListViewModel::class.java)
        viewModelSportList = ViewModelProvider(this).get(SportListViewModel::class.java)
        viewModelLeagueList = ViewModelProvider(this).get(LeagueListViewModel::class.java)
        viewModelUserTagSelect = ViewModelProvider(this).get(UserTagSelectViewModel::class.java)
        viewModelUserAnnouncements = ViewModelProvider(this).get(UserAnnouncementListViewModel::class.java)
        viewModelUserLeagues = ViewModelProvider(this).get(UserLeagueListViewModel::class.java)
        viewModelUserLeagueTableNames = ViewModelProvider(this).get(UserLeagueTableNameViewModel::class.java)
        viewModelUserNews = ViewModelProvider(this).get(UserNewsListViewModel::class.java)
        viewModelUserTagEntertainment = ViewModelProvider(this).get(UserTagEntertainmentViewModel::class.java)
        viewModelUserCityEntertainment = ViewModelProvider(this).get(UserCityEntertainmentViewModel::class.java)

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
            //getUserCityEntertainment()
            //getUserTagEntertainment()
            //getUserNews()
            //getUserLeagueTableNames()
            //getUserLeagues()
            //getUserAnnouncements()
            //saveUserTags()
            //getLeagues()
            //getSports()
            //getEntertainments()
            //getNews()
            //getUserAnnouncementRead()
            //getUserSurveysRead()
            //getUserSurveys()
            //getSurveys()
            //getUserTags()
            //userTagDelete()
            //getCategoryTag()
            //getTagList()
            //changePassword()
            doUserLogin(it, progressBar3)
            //doSignUp()
            //fetchUserList()
//            val intent = Intent(it.context, MainAppActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }

    private fun getUserCityEntertainment() {
        viewModelUserCityEntertainment.getUserCityEntertainment()
        viewModelUserCityEntertainment.userCityEntertainment.observe(this, Observer { response ->
            println(response.entertainmentID)
        })
    }

    private fun getUserTagEntertainment() {
        viewModelUserTagEntertainment.getUserTagEntertainment()
        viewModelUserTagEntertainment.userTagEntertainment.observe(this, Observer { response ->
            println(response.entertainmentID)
        })
    }

    private fun getUserNews() {
        viewModelUserNews.getUserNews()
        viewModelUserNews.userNewsList.observe(this, Observer { response ->
            println(response.newsID)
        })
    }

    private fun getUserLeagueTableNames() {
        viewModelUserLeagueTableNames.getUserLeagueTableNames()
        viewModelUserLeagueTableNames.leagueTableNames.observe(this, Observer { response ->
            println(response.leagueTableName)
        })
    }

    private fun getUserLeagues() {
        viewModelUserLeagues.getUserLeagues()
        viewModelUserLeagues.leagueList.observe(this, Observer { response ->
            println(response.basketballID)
        })
    }

    private fun getUserAnnouncements() {
        viewModelUserAnnouncements.getUserAnnouncements()
        viewModelUserAnnouncements.announcementList.observe(this, Observer { response ->
            println(response.announcementID)
            println(response.announcementContent)
            println(response.announcementDate)
            println(response.announcementTitle)
        })
    }

    private fun saveUserTags() {
        viewModelUserTagSelect.saveUserTags()
        viewModelUserTagSelect.selectTags.observe(this, Observer { response ->
            println(response.message)
        })
    }

    private fun getLeagues() {
        viewModelLeagueList.getLeagueList()
        viewModelLeagueList.leagueList.observe(this, Observer { response ->
            println(response.leagueID)
            println(response.leagueName)
            println(response.leagueUrl)
            println(response.leagueCountry)
            println(response.sportID)
            println(response.sportName)
        })
    }

    private fun getSports() {
        viewModelSportList.getSportList()
        viewModelSportList.sportList.observe(this, Observer { response ->
            println(response.sportID)
            println(response.sportName)
            println(response.leagueTableName)
        })
    }

    private fun getEntertainments() {
        viewModelEntertainmentList.getEntertainmentList()
        viewModelEntertainmentList.entertainmentList.observe(this, Observer { response ->
            println(response.entertainmentID)
        })
    }

    private fun getNews() {
        viewModelNewsList.getNewsList()
        viewModelNewsList.newsList.observe(this, Observer { response ->
            println(response.newsID)
            println(response.newsTitle)
            println(response.newsDescription)
            println(response.newsCategoryID)
            println(response.newsCategoryName)
        })
    }

    private fun getUserAnnouncementRead() {
        viewModelUserAnnouncementRead.getUserAnnouncementRead()
        viewModelUserAnnouncementRead.announcementRead.observe(this, Observer { response ->
            println(response.status)
            println(response.message)
        })
    }

    private fun getUserSurveysRead() {
        viewModelUserSurveysRead.getUserSurveyRead()
        viewModelUserSurveysRead.surveyread.observe(this, Observer { response ->
            println(response.status)
            println(response.message)
        })
    }

    private fun getUserSurveys() {
        viewModelUserSurveyList.getUserTags()
        viewModelUserSurveyList.userSurveyList.observe(this, Observer { response ->
            println(response.surveyID)
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

    private fun doUserLogin(view: View, progressBar: ProgressBar) {
        if(textInputEmailLogin.editText!!.text.isNotEmpty() && textInputPasswordLogin.editText!!.text.isNotEmpty()){
            progressBar3.visibility = View.VISIBLE
            val email = textInputEmailLogin.editText!!.text.trim().toString()
            val password = textInputPasswordLogin.editText!!.text.trim().toString()
            viewModelUserLogin.postUserLogin(email, password, view, progressBar)
            viewModelUserLogin.myUserLogin.observe(this, Observer { response ->

                println(response.userInformation)
                println(response.userInformation.userName)
                println(response.userInformation.userMail)
                println(response.userInformation.userPassword)
                println(response.token)
            })
        }else{
               Snackbar.make(view,"Lütfen E-posta veya Şifrenizi kontrol ediniz",Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun fetchUserList() {
        viewModel.getUserList()
        viewModel.myUserList.observe(this, Observer { response ->
            println(response.userName)
            println(response.userCity)
            println(response.regDate)
        })
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}