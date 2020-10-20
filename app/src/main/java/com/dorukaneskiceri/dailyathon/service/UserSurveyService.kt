package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.SurveyListModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserTagListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserSurveyService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserSurveyListGET::class.java)

    suspend fun getUserSurveys(token: String, userID: Int): Response<ArrayList<SurveyListModel>> {
        return api.getUserSurveys(token, userID)
    }
}