package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class CategoryListModel(
    @SerializedName("CategoryID")
    val categoryID: Int,
    @SerializedName("CategoryName")
    val categoryName: String
)