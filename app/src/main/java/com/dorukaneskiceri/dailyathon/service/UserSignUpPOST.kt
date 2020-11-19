package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*
import kotlin.collections.HashMap

interface UserSignUpPOST {

    @FormUrlEncoded
    @POST("sign-up/user")
    suspend fun userSignUp(
        @FieldMap map: HashMap<String, String>,
        @Field("RegDate") userRegDate: Date
    ): Response<UserResponseMessage>
}