package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserTagSelectService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserTagSelectPOST::class.java)

    suspend fun saveUserTags(
        token: String,
        userID: Int,
        tagName: String,
        regDate: String
    ): Response<UserResponseMessage>{
        return api.saveUserTags(token, userID, tagName, regDate)
    }
}