package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserLeagueTableNameModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserNewsListModel
import com.dorukaneskiceri.dailyathon.service.UserLeagueTableNameService
import com.dorukaneskiceri.dailyathon.service.UserNewsListService
import kotlinx.coroutines.*

class UserNewsListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListNews = ArrayList<UserNewsListModel>()
    var userNewsList = MutableLiveData<UserNewsListModel>()

    fun getUserNews(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserNewsListService().getUserNews(
                "",
                1
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListNews = it
                        arrayListNews.forEach {
                            userNewsList.value = it
                            println("Kullanıcı haberleri okundu")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}