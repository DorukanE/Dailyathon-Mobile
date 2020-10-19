package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChangePasswordService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ChangePasswordPOST::class.java)

    suspend fun changePassword(
        userName: String,
        userSurname: String,
        userEmail: String,
        newPassword: String,
        userDate: String
    ): Response<UserResponseMessage>{
        return api.changePassword(userName,userSurname,userEmail,newPassword,userDate)
    }
}