package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
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

    fun getLeagueList(token: String, view: View) {
        getDataFromAPI(token, view)
    }

    private fun getDataFromAPI(token: String, view: View) {
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
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}