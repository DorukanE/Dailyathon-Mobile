package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface UserCityEntertainmentPOST {

    @FormUrlEncoded
    @POST("city-entertainment")
    suspend fun getUserCityEntertainment(
        @Header("token") token: String,
        @Field("UserCity") userCity: String
    ): Response<ArrayList<UserEntertainmentModel>>
}