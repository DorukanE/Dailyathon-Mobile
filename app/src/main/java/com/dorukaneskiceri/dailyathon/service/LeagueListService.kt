package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.LeagueListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LeagueListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LeagueListGET::class.java)

    suspend fun getLeagues(token: String): Response<ArrayList<LeagueListModel>> {
        return api.getLeagues(token)
    }
}