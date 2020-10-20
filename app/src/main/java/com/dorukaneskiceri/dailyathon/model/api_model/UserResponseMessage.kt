package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class UserResponseMessage(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)