package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserSurveyListModel
import retrofit2.Response
import retrofit2.http.*

interface UserSurveyListPOST {

    @FormUrlEncoded
    @POST("survey-user-list")
    suspend fun getUserSurveys(
        @Header("token") token: String,
        @Field(value = "UserID", encoded = true) userID: Int
    ): Response<ArrayList<UserSurveyListModel>>
}