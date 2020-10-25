package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserAnnouncementListModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserLeagueListModel
import com.dorukaneskiceri.dailyathon.service.UserAnnouncementListService
import com.dorukaneskiceri.dailyathon.service.UserLeagueListService
import kotlinx.coroutines.*

class UserLeagueListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListLeagues = ArrayList<UserLeagueListModel>()
    var leagueList = MutableLiveData<UserLeagueListModel>()

    fun getUserLeagues(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserLeagueListService().getUserLeagues(
                "",
                1,
                "tblBasketball"
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListLeagues = it
                        arrayListLeagues.forEach {
                            leagueList.value = it
                            println("Kullanıcı ligleri okundu")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}