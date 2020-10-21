package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class NewsListModel (
    @SerializedName("NewsID")
    val newsID: Int,
    @SerializedName("NewsTitle")
    val newsTitle: String,
    @SerializedName("NewsDescription")
    val newsDescription: String,
    @SerializedName("NewsCategoryID")
    val newsCategoryID: Int,
    @SerializedName("NewsCategoryName")
    val newsCategoryName: String
)