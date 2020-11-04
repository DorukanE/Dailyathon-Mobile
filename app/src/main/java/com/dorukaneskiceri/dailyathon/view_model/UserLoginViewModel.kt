package com.dorukaneskiceri.dailyathon.view_model

import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.activity.MainAppActivity
import com.dorukaneskiceri.dailyathon.login_signup.LoginActivity
import com.dorukaneskiceri.dailyathon.model.api_model.UserLoginModel
import com.dorukaneskiceri.dailyathon.service.UserLoginService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.coroutines.*

class UserLoginViewModel: ViewModel() {
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    val myUserLogin = MutableLiveData<UserLoginModel>()

    fun postUserLogin(email:String, password:String, view: View, progressBar: ProgressBar, sharedPreferences: SharedPreferences){
        getDataFromAPI(email, password, view, progressBar, sharedPreferences)
    }

    fun postUserLoginProfile(email: String, password: String){
        getDataFromAPIProfile(email, password)
    }

    private fun getDataFromAPIProfile(email: String, password: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserLoginService().userLogin(email, password)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        myUserLogin.value = it
                        println("Kullanıcı bilgileri okundu")
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }

    private fun getDataFromAPI(email: String, password: String, view: View, progressBar: ProgressBar, sharedPreferences: SharedPreferences){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserLoginService().userLogin(email,password)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        myUserLogin.value = it
                        println("Giriş başarılı")
                    }
                    sharedPreferences.edit().putBoolean("userIsLogin",true).apply()
                    progressBar.visibility = View.INVISIBLE
                    openLoginScreen(view)
                }else{
                    progressBar.visibility = View.INVISIBLE
                    println(response.message())
                    Snackbar.make(view,"Lütfen E-posta veya Şifrenizi kontrol ediniz", Snackbar.LENGTH_SHORT).show()
                }
            }
            if(job!!.isActive){
                job!!.cancel()
            }
        }
    }

    private fun openLoginScreen(view: View) {
        val intent = Intent(view.context, MainAppActivity::class.java)
        view.context.startActivity(intent)
    }
}