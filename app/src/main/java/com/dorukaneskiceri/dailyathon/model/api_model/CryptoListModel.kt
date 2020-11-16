package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class CryptoListModel(
    @SerializedName("CriptoID")
    val cryptoID: Int,
    @SerializedName("Currency")
    val cryptoCurrency: String,
    @SerializedName("ChangeWeek")
    val cryptoChangeWeek: String,
    @SerializedName("ChangeDay")
    val cryptochangeDay: String,
    @SerializedName("Price")
    val cryptoPrice: String,
    @SerializedName("Code")
    val cryptoCode: String,
    @SerializedName("Name")
    val cryptoName: String,
)