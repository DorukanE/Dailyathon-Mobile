package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class UserResponseMessage(
    @SerializedName("message")
    val message: String
)