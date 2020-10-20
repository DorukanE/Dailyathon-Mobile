package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.SurveyListModel
import com.dorukaneskiceri.dailyathon.service.SurveyListService
import kotlinx.coroutines.*

class SurveyListViewModel : ViewModel() {

    private var job: Job? = null
    private var arrayListSurvey = ArrayList<SurveyListModel>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    var surveyList = MutableLiveData<SurveyListModel>()

    fun getSurveys() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = SurveyListService().getSurveys()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        arrayListSurvey = it
                        arrayListSurvey.forEach {
                            surveyList.value = it
                            println("Anket okuması başarılı")
                        }
                    }
                } else {
                    println(response.message())
                }
            }
        }
    }
}