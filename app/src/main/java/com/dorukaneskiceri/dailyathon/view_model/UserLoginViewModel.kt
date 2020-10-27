package com.dorukaneskiceri.dailyathon.view_model

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.activity.MainAppActivity
import com.dorukaneskiceri.dailyathon.login_signup.LoginActivity
import com.dorukaneskiceri.dailyathon.model.api_model.UserLoginModel
import com.dorukaneskiceri.dailyathon.service.UserLoginService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class UserLoginViewModel: ViewModel() {
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    val myUserLogin = MutableLiveData<UserLoginModel>()

    fun postUserLogin(email:String, password:String, view: View){
        getDataFromAPI(email, password, view)
    }

    private fun getDataFromAPI(email: String, password: String, view: View){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserLoginService().userLogin(email,password)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        myUserLogin.value = it
                        println("Giriş başarılı")
                    }
                    openLoginScreen(view)
                }else{
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
        LoginActivity().finish()
    }
}