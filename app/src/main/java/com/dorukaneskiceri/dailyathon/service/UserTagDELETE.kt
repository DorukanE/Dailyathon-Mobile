package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import retrofit2.Response
import retrofit2.http.*

interface UserTagDELETE {

    @FormUrlEncoded
    @HTTP(method = "DELETE",path = "user-tag", hasBody = true)
    suspend fun userTagDelete(
        @Header("token") token: String,
        @Field("UserID") userID: Int,
        @Field("TagName") tagName: String
    ): Response<UserResponseMessage>
}