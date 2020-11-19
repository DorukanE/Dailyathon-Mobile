package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.LeagueListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface LeagueListGET {

    @GET("league")
    suspend fun getLeagues(
        @Header("token") token: String
    ): Response<ArrayList<LeagueListModel>>
}