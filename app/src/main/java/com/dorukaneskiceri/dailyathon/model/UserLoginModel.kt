package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class UserLoginModel(
    @SerializedName("userInformation")
    val userInformation: UserListModel,
    @SerializedName("token")
    val token: String
)
