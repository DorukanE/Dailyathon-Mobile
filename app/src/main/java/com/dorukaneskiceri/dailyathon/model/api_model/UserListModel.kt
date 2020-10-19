package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class UserListModel(
    @SerializedName("UserID")
    val userId: Int,
    @SerializedName("UserName")
    val userName: String,
    @SerializedName("UserSurname")
    val userSurname: String,
    @SerializedName("UserMail")
    val userMail: String,
    @SerializedName("UserPassword")
    val userPassword: String,
    @SerializedName("UserDate")
    val userDate: String,
    @SerializedName("UserProfession")
    val userProfession: String,
    @SerializedName("UserCity")
    val userCity: String,
    @SerializedName("RegDate")
    val regDate: String
)