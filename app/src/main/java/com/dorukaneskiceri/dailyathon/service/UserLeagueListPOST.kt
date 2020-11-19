package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface UserLeagueListPOST {

    @FormUrlEncoded
    @POST("user-league")
    suspend fun getUserLeagues(
        @Header("token") token: String,
        @Field("UserID") userID: Int,
        @Field("LeagueTableName") leagueTableName: String
    ): Response<ArrayList<UserLeagueListModel>>
}