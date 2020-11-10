package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface UserNewsListPOST {

    @FormUrlEncoded
    @POST("user-news")
    suspend fun getUserNews(
        @Header("token") token: String,
        @Field("UserID") userID: Int
    ): Response<ArrayList<NewsListModel>>
}