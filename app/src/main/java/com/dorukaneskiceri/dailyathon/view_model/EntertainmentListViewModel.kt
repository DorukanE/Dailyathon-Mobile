package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.EntertainmentListModel
import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel
import com.dorukaneskiceri.dailyathon.service.EntertainmentListService
import com.dorukaneskiceri.dailyathon.service.NewsListService
import kotlinx.coroutines.*

class EntertainmentListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListEntertainments = ArrayList<EntertainmentListModel>()
    var entertainmentList = MutableLiveData<EntertainmentListModel>()

    fun getEntertainmentList(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = EntertainmentListService().getEntertainments()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListEntertainments = it
                        arrayListEntertainments.forEach {
                            entertainmentList.value = it
                            println("Eğlencelerin okuması başarılı")
                        }
                    }
                }
            }
        }
    }
}