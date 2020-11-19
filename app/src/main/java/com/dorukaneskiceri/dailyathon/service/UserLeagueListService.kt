package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserLeagueListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserLeagueListPOST::class.java)

    suspend fun getUserLeagues(token: String, userID: Int, leagueTableName: String): Response<ArrayList<UserLeagueListModel>> {
        return api.getUserLeagues(token, userID, leagueTableName)
    }
}