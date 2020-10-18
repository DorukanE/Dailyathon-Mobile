package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserLoginModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserLoginPOST {

    @FormUrlEncoded
    @POST("login/user")
    suspend fun userLogin(
        @Field("UserEmail") userEmail: String,
        @Field("UserPassword") userPassword: String,
    ): Response<UserLoginModel>
}