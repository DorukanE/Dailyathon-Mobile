package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.EntertainmentListModel
import retrofit2.Response
import retrofit2.http.GET

interface EntertainmentListGET {

    @GET("entertainment")
    suspend fun getEntertainments(): Response<ArrayList<EntertainmentListModel>>
}