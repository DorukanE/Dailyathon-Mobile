package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.NewsListModel
import com.dorukaneskiceri.dailyathon.service.UserNewsListService
import kotlinx.coroutines.*

class UserNewsListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListNews = ArrayList<NewsListModel>()
    var userNewsList = MutableLiveData<NewsListModel>()

    fun getUserNews(token: String, userID: Int, view: View){
        getDataFromAPI(token, userID, view)
    }

    private fun getDataFromAPI(token: String, userID: Int, view: View){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserNewsListService().getUserNews(
                token,
                userID
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
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}