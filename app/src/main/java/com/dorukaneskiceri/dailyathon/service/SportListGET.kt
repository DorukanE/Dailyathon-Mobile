package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.SportListModel
import retrofit2.Response
import retrofit2.http.GET

interface SportListGET {

    @GET("sport")
    suspend fun getSports(): Response<ArrayList<SportListModel>>
}