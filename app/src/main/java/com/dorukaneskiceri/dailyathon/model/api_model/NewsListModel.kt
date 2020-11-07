package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class NewsListModel (
    @SerializedName("NewsID")
    val newsID: Int,
    @SerializedName("NewsTitle")
    val newsTitle: String,
    @SerializedName("NewsDescription")
    val newsDescription: String,
    @SerializedName("NewsImage")
    val newsImage: String,
    @SerializedName("TagID")
    val tagID: Int,
    @SerializedName("TagName")
    val tagName: String
)