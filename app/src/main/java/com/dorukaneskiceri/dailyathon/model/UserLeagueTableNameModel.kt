package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class UserLeagueTableNameModel(
    @SerializedName("LeagueTableName")
    val leagueTableName: String,
    @SerializedName("SportName")
    val sportName: String
)