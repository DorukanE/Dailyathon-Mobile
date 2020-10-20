package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.SurveyListModel
import retrofit2.Response
import retrofit2.http.GET

interface SurveyListGET {

    @GET("survey")
    suspend fun getSurveys(): Response<ArrayList<SurveyListModel>>
}