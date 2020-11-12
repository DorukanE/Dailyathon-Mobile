package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserSurveyListModel
import com.dorukaneskiceri.dailyathon.service.UserSurveyListService
import kotlinx.coroutines.*

class UserSurveyListViewModel : ViewModel() {

    private var job: Job? = null
    private var arrayListSurvey = ArrayList<UserSurveyListModel>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    var userSurveyList = MutableLiveData<UserSurveyListModel>()

    fun getUserTags(token: String, userID: Int) {
        getDataFromAPI(token, userID)
    }

    private fun getDataFromAPI(token: String, userID: Int) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserSurveyListService().getUserSurveys(
                token,
                userID
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