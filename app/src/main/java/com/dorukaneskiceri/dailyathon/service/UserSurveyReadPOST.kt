package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserResponseMessage
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*

interface UserSurveyReadPOST {

    @FormUrlEncoded
    @POST("survey-read")
    suspend fun userSurveyRead(
        @Header("token") token: String,
        @Field("UserID") userID: Int,
        @Field("SurveyListID") surveyListID: Int,
        @Field("RegDate") regDate: Date
    ): Response<UserResponseMessage>
}