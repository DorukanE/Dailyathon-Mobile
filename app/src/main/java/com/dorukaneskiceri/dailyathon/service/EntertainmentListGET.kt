package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.EntertainmentListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface EntertainmentListGET {

    @GET("entertainment")
    suspend fun getEntertainments(
        @Header("token") token: String
    ): Response<ArrayList<EntertainmentListModel>>
}