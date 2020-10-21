package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.LeagueListModel
import com.dorukaneskiceri.dailyathon.model.api_model.LeagueListService
import com.dorukaneskiceri.dailyathon.model.api_model.SportListModel
import com.dorukaneskiceri.dailyathon.service.SportListService
import kotlinx.coroutines.*

class LeagueListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListLeague = ArrayList<LeagueListModel>()
    var leagueList = MutableLiveData<LeagueListModel>()

    fun getLeagueList(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = LeagueListService().getLeagues()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListLeague = it
                        arrayListLeague.forEach {
                            leagueList.value = it
                            println("Liglerin okuması başarılı")
                        }
                    }
                }
            }
        }
    }
}