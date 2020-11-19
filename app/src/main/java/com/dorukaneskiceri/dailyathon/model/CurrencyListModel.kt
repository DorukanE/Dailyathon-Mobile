package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class CurrencyListModel(
    @SerializedName("CurrencyID")
    val currencyID: Int,
    @SerializedName("Name")
    val currencyName: String,
    @SerializedName("Code")
    val currencyCode: String,
    @SerializedName("Buying")
    val currencyBuyValue: String,
    @SerializedName("Selling")
    val currencySellValue: String,
    @SerializedName("Rate")
    val currencyRate: String,
    @SerializedName("Datetime")
    val currencyDatetime: String,
)