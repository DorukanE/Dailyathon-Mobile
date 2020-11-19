package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.NewsListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsListGET::class.java)

    suspend fun getNews(token: String): Response<ArrayList<NewsListModel>>{
        return api.getNews(token)
    }
}