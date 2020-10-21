package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class SportListModel (
    @SerializedName("SportID")
    val sportID: Int,
    @SerializedName("SportName")
    val sportName: String,
    @SerializedName("LeagueTableName")
    val leagueTableName: String
)