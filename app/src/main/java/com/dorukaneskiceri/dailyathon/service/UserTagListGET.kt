package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserTagListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP
import retrofit2.http.Header

interface UserTagListGET {

    @FormUrlEncoded
    @HTTP(method = "GET", path = "user-tag", hasBody = true)
    suspend fun getUserTags(
        @Header("token") token: String,
        @Field("UserID") userID: Int
    ): Response<ArrayList<UserTagListModel>>
}