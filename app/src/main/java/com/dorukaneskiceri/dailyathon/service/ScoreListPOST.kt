package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ScoreListPOST {

    @FormUrlEncoded
    @POST("standings-find")
    suspend fun getScoreList(
        @Header("token") token: String,
        @Field("LeagueID") leagueID: Int,
        @Field("SportID") sportID: Int
    ): Response<ArrayList<UserLeagueListModel>>
}