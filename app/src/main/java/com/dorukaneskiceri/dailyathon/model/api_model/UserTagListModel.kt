package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class UserTagListModel(
    @SerializedName("TagID")
    val tagID: Int,
    @SerializedName("TagName")
    val tagName: String,
    @SerializedName("CategoryID")
    val categoryID: Int,
    @SerializedName("CategoryName")
    val categoryName: String
)