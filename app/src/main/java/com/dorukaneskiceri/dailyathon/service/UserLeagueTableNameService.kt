package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserLeagueTableNameModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserLeagueTableNameService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserLeagueTableNamePOST::class.java)

    suspend fun getUserLeagueTableNames(token: String, userID: Int): Response<ArrayList<UserLeagueTableNameModel>> {
        return api.getUserLeagueTableNames(token, userID)
    }
}