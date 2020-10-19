package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class CategoryTagModel(
    @SerializedName("TagID")
    val tagID: Int,
    @SerializedName("TagName")
    val tagName: String,
    @SerializedName("CategoryName")
    val categoryName: String
)