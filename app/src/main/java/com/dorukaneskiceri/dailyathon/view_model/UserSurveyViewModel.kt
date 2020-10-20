package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.SurveyListModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserTagListModel
import com.dorukaneskiceri.dailyathon.service.UserSurveyService
import com.dorukaneskiceri.dailyathon.service.UserTagListService
import kotlinx.coroutines.*

class UserSurveyViewModel : ViewModel() {

    private var job: Job? = null
    private var arrayListSurvey = ArrayList<SurveyListModel>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    var userSurveyList = MutableLiveData<SurveyListModel>()

    fun getUserTags() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserSurveyService().getUserSurveys(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOjEsImlhdCI6MTYwMzIwNDk0NSwiZXhwIjoxNjAzMjA1NjY1fQ.i5XMCr7XAoMGGjuvTSsTidyKo5YZpUdJoBnYm1bd1g0",
                1
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        arrayListSurvey = it
                        arrayListSurvey.forEach {
                            userSurveyList.value = it
                            println("Kullanıcı anketleri okundu")
                        }
                    }
                } else {
                    response.message()
                }
            }
        }
    }
}