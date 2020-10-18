package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class UserLoginModel(
    @SerializedName("userInformation")
    val userInformation: UserListModel,
    @SerializedName("token")
    val token: String
)
