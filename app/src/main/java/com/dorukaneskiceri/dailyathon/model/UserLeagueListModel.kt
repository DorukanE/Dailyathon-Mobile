package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class UserLeagueListModel(
    @SerializedName("FootballID")
    val footballID: Int,
    @SerializedName("BasketballID")
    val basketballID: Int,
    @SerializedName("SequenceNo")
    val sequenceNo: Int,
    @SerializedName("TeamName")
    val teamName: String,
    @SerializedName("TeamLogoUrl")
    val teamLogoUrl: String,
    @SerializedName("O")
    val play: Int,
    @SerializedName("G")
    val win: Int,
    @SerializedName("B")
    val draw: Int,
    @SerializedName("M")
    val lose: Int,
    @SerializedName("A")
    val score: Int,
    @SerializedName("Y")
    val counterScore: Int,
    @SerializedName("AV")
    val average: String,
    @SerializedName("P")
    val point: Int,
    @SerializedName("LeagueID")
    val leagueID: Int
)
