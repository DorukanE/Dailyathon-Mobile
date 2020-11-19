package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.NewsListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NewsListGET {

    @GET("news")
    suspend fun getNews(
        @Header("token") token: String
    ): Response<ArrayList<NewsListModel>>
}