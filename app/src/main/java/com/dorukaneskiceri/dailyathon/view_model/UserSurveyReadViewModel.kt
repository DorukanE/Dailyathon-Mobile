package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.UserSurveyReadService
import kotlinx.coroutines.*
import java.util.*

class UserSurveyReadViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    var surveyread = MutableLiveData<UserResponseMessage>()

    fun getUserSurveyRead(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val currentTime: Date = Calendar.getInstance().time
            val response = UserSurveyReadService().userSurveyRead(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOjEsImlhdCI6MTYwMzIwNjk3MCwiZXhwIjoxNjAzMjA3NjkwfQ.QANRAhpq_6vhaGtRKhhCqrlt9XgNL8Dr35XJKEo1cVg",
                1,
                21,
                currentTime
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        surveyread.value = it
                        println("Kullanıcının anket okuma durumu kaydedildi")
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}