package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserTagDeleteService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserTagDELETE::class.java)

    suspend fun getUserFind(token: String, userID: Int, tagName: String): Response<UserResponseMessage> {
        return api.userTagDelete(token, userID, tagName)
    }
}