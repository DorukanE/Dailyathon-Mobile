package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.SportListModel
import com.dorukaneskiceri.dailyathon.service.SportListService
import kotlinx.coroutines.*

class SportListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListSport = ArrayList<SportListModel>()
    var sportList = MutableLiveData<SportListModel>()

    fun getSportList(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = SportListService().getSports()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListSport = it
                        arrayListSport.forEach {
                            sportList.value = it
                            println("Sporların okuması başarılı")
                        }
                    }
                }
            }
        }
    }
}