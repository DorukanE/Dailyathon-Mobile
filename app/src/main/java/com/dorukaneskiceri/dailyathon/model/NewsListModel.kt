package com.dorukaneskiceri.dailyathon.model

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
    @SerializedName("Content")
    val content: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("TagID")
    val tagID: Int,
    @SerializedName("TagName")
    val tagName: String
)