package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.UserSignUpService
import kotlinx.coroutines.*
import java.util.*

class UserSignUpViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    val myUserSignUp = MutableLiveData<UserResponseMessage>()

    fun postUserSignUp(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val currentTime: Date = Calendar.getInstance().time
            val response = UserSignUpService().userSignUp(
                "Coşkun",
                "Ağa",
                "coskun@gmail.com",
                "ahmet",
                "1236-01-03",
                "Makine Mühendisi",
                "İstanbul",
                currentTime)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        myUserSignUp.value = it
                        println("Kayıt başarılı")
                    }
                }else{
                    println("Kayıt Başarısız")
                }
            }
        }
    }
}