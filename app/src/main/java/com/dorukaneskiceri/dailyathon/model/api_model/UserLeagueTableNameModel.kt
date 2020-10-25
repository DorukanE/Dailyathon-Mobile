package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class UserLeagueTableNameModel(
    @SerializedName("LeagueTableName")
    val leagueTableName: String
)