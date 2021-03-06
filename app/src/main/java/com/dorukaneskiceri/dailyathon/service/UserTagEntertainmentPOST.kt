package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface UserTagEntertainmentPOST {

    @FormUrlEncoded
    @POST("tag-entertainment")
    suspend fun getUserTagEntertainment(
        @Header("token") token: String,
        @Field("UserID") userID: Int
    ): Response<ArrayList<UserEntertainmentModel>>
}