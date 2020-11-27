package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.NewsListModel
import com.dorukaneskiceri.dailyathon.service.NewsListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class NewsListViewModel : ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListNews = ArrayList<NewsListModel>()
    var newsList = MutableLiveData<NewsListModel>()

    fun getNewsList(token: String, view: View){
        getDataFromAPI(token, view)
    }

    private fun getDataFromAPI(token: String, view: View) {
        view2 = view
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
                }else{
                    println(response.message())
                }
            }
        }
    }
}