package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel
import com.dorukaneskiceri.dailyathon.service.UserLeagueListService
import kotlinx.coroutines.*

class UserLeagueListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListLeagues = ArrayList<UserLeagueListModel>()
    var leagueList = MutableLiveData<UserLeagueListModel>()

    fun getUserLeagues(token: String, userID: Int, leagueTableName: String, view: View){
        getDataFromAPI(token, userID, leagueTableName, view)
    }

    private fun getDataFromAPI(token: String, userID: Int, leagueTableName: String, view: View){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserLeagueListService().getUserLeagues(
                token,
                userID,
                leagueTableName
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
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}