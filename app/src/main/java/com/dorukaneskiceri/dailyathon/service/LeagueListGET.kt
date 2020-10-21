package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.LeagueListModel
import retrofit2.Response
import retrofit2.http.GET

interface LeagueListGET {

    @GET("league")
    suspend fun getLeagues(): Response<ArrayList<LeagueListModel>>
}