package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserLeagueTableNameModel
import com.dorukaneskiceri.dailyathon.service.UserLeagueTableNameService
import kotlinx.coroutines.*

class UserLeagueTableNameViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListTableName = ArrayList<UserLeagueTableNameModel>()
    var leagueTableNames = MutableLiveData<UserLeagueTableNameModel>()

    fun getUserLeagueTableNames(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserLeagueTableNameService().getUserLeagueTableNames(
                "",
                1
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListTableName = it
                        arrayListTableName.forEach {
                            leagueTableNames.value = it
                            println("Kullanıcı lig tabloları? okundu")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}