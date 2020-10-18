package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserListModel
import com.dorukaneskiceri.dailyathon.utils.Constant.Companion.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserListService {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserListGET::class.java)

    suspend fun getUserList(): Response<ArrayList<UserListModel>>{
        return api.getUserList()
    }
}