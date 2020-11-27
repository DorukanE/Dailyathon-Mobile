package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel
import com.dorukaneskiceri.dailyathon.service.ScoreListService
import kotlinx.coroutines.*

class ScoreListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListScore = ArrayList<UserLeagueListModel>()
    val scoreList = MutableLiveData<UserLeagueListModel>()

    fun getScoreList(token: String, leagueID: Int, sportID: Int, view: View){
        getDataFromAPI(token, leagueID, sportID, view)
    }

    private fun getDataFromAPI(token: String, leagueID: Int, sportID: Int, view: View) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = ScoreListService().getScoreList(token, leagueID, sportID)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListScore = it
                        arrayListScore.forEach {
                            scoreList.value = it
                            println("Skor tablosu okundu")
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