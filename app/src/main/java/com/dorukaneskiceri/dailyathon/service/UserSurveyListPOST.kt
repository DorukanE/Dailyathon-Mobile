package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserSurveyListModel
import retrofit2.Response
import retrofit2.http.*

interface UserSurveyListPOST {

    @FormUrlEncoded
    @POST("surveyUserList")
    suspend fun getUserSurveys(
        @Header("token") token: String,
        @Field(value = "UserID", encoded = true) userID: Int
    ): Response<ArrayList<UserSurveyListModel>>
}