package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.LeagueListModel
import com.dorukaneskiceri.dailyathon.service.LeagueListService
import kotlinx.coroutines.*

class LeagueListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListLeague = ArrayList<LeagueListModel>()
    var leagueList = MutableLiveData<LeagueListModel>()

    fun getLeagueList(token: String) {
        getDataFromAPI(token)
    }

    private fun getDataFromAPI(token: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = LeagueListService().getLeagues(token)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListLeague = it
                        arrayListLeague.forEach {
                            leagueList.value = it
                            println("Liglerin okuması başarılı")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}