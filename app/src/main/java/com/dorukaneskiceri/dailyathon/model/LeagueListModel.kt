package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class LeagueListModel (
    @SerializedName("LeagueID")
    val leagueID: Int,
    @SerializedName("LeagueName")
    val leagueName:String,
    @SerializedName("LeagueUrl")
    val leagueUrl: String,
    @SerializedName("LeagueCountry")
    val leagueCountry: String,
    @SerializedName("SportID")
    val sportID: Int,
    @SerializedName("SportName")
    val sportName: String
)