package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class UserResponseMessage(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)