package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ChangePasswordPOST {

    @FormUrlEncoded
    @POST("forgotpassword")
    suspend fun changePassword(
        @Field("UserName") userName: String,
        @Field("UserSurname") userSurname: String,
        @Field("UserEmail") userEmail: String,
        @Field("NewPassword") newPassword: String,
        @Field("UserDate") userDate: String,
    ): Response<UserResponseMessage>
}