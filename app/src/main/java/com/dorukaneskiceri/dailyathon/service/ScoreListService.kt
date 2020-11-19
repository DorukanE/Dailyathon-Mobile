package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScoreListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ScoreListPOST::class.java)

    suspend fun getScoreList(token: String, leagueID: Int, sportID: Int): Response<ArrayList<UserLeagueListModel>>{
        return api.getScoreList(token, leagueID, sportID)
    }
}