package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class StockListModel(
    @SerializedName("StockID")
    val stockID: Int,
    @SerializedName("LastPrice")
    val stockLastPrice: String,
    @SerializedName("Min")
    val stockMin: String,
    @SerializedName("Max")
    val stockLastMax: String,
    @SerializedName("Time")
    val stockTime: String,
    @SerializedName("Text")
    val stockText: String,
    @SerializedName("Code")
    val stockCode: String,
)