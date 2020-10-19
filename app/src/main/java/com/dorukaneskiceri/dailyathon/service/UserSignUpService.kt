package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class UserSignUpService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserSignUpPOST::class.java)

    suspend fun userSignUp(userName: String,
                           userSurname: String,
                           userEmail: String,
                           userPassword: String,
                           userDate: String,
                           userProfession: String,
                           userCity: String,
                           userRegDate: Date
    ): Response<UserResponseMessage> {
        return api.userSignUp(userName,userSurname,userEmail,userPassword,userDate,userProfession,userCity,userRegDate)
    }
}