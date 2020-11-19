package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.NewsListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserNewsListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserNewsListPOST::class.java)

    suspend fun getUserNews(token: String, userID: Int): Response<ArrayList<NewsListModel>>{
        return api.getUserNews(token, userID)
    }
}