package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*

interface UserTagSelectPOST {

    @FormUrlEncoded
    @POST("user-tag")
    suspend fun saveUserTags(
        @Header("token") token: String,
        @Field("UserID") userID: Int,
        @Field("TagName") tagName: String,
        @Field("RegDate") RegDate: String,
    ): Response<UserResponseMessage>
}