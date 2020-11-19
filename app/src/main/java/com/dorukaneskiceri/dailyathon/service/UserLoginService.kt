package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserLoginModel
import com.dorukaneskiceri.dailyathon.utils.Constant.Companion.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserLoginService {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserLoginPOST::class.java)

    suspend fun userLogin(userEmail: String, userPassword: String): Response<UserLoginModel> {
        return api.userLogin(userEmail,userPassword)
    }
}