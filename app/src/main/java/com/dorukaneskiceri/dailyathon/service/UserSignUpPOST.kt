package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserListModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserSignUpMessage
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

interface UserSignUpPOST {

    @FormUrlEncoded
    @POST("sign-up/user")
    suspend fun userSignUp(
        @Field("UserName") userName: String,
        @Field("UserSurname") userSurname: String,
        @Field("UserEmail") userEmail: String,
        @Field("UserPassword") userPassword: String,
        @Field("UserDate") userDate: String,
        @Field("UserProfession") userProfession: String,
        @Field("UserCity") userCity: String,
        @Field("RegDate") userRegDate: Date,
    ): Response<UserSignUpMessage>

}