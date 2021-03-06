package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class TagListModel(
  @SerializedName("TagID")
  val tagID: Int,
  @SerializedName("TagName")
  val tagName: String,
  @SerializedName("CategoryID")
  val categoryID: Int
)