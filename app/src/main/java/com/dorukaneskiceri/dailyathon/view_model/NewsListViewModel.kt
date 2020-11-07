package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel
import com.dorukaneskiceri.dailyathon.model.api_model.TagListModel
import com.dorukaneskiceri.dailyathon.service.NewsListService
import com.dorukaneskiceri.dailyathon.service.TagListService
import kotlinx.coroutines.*

class NewsListViewModel : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListNews = ArrayList<NewsListModel>()
    var newsList = MutableLiveData<NewsListModel>()

    fun getNewsList(token: String){
        getDataFromAPI(token)
    }

    private fun getDataFromAPI(token: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = NewsListService().getNews(token)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListNews = it
                        arrayListNews.forEach {
                            newsList.value = it
                            println("Haberlerin okuması başarılı")
                        }
                    }
                }
            }
        }
    }
}