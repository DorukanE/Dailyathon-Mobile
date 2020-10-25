package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserSurveyListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserSurveyListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserSurveyListPOST::class.java)

    suspend fun getUserSurveys(token: String, userID: Int): Response<ArrayList<UserSurveyListModel>>{
        return api.getUserSurveys(token, userID)
    }
}