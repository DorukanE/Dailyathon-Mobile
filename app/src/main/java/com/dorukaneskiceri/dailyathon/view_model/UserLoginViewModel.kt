package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserLoginModel
import com.dorukaneskiceri.dailyathon.service.UserLoginPOST
import com.dorukaneskiceri.dailyathon.service.UserLoginService
import kotlinx.coroutines.*

class UserLoginViewModel: ViewModel() {
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    val myUserLogin = MutableLiveData<UserLoginModel>()

    fun postUserLogin(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserLoginService().userLogin("doruk@gmail.com","doruk")
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        myUserLogin.value = it
                        println("Okuması başarılı")
                    }
                }
            }
            if(job!!.isActive){
                job!!.cancel()
            }
        }
    }
}