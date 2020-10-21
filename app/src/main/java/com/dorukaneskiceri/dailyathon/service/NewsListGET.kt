package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel
import retrofit2.Response
import retrofit2.http.GET

interface NewsListGET {

    @GET("news")
    suspend fun getNews(): Response<ArrayList<NewsListModel>>
}