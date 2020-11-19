package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserTagListModel
import retrofit2.Response
import retrofit2.http.*

interface UserTagListPOST {

    @FormUrlEncoded
    @POST("user-tag")
    suspend fun getUserTags(
        @Header("token") token: String,
        @Field("UserID") userID: Int
    ): Response<ArrayList<UserTagListModel>>
}