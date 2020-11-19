package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserLeagueTableNameModel
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface UserLeagueTableNamePOST {

    @FormUrlEncoded
    @POST("league-table")
    suspend fun getUserLeagueTableNames(
        @Header("token") token: String,
        @Header("UserID") userID: Int
    ): Response<ArrayList<UserLeagueTableNameModel>>
}