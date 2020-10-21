package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class EntertainmentListModel(
    @SerializedName("EntertainmentID")
    val entertainmentID: Int,
    @SerializedName("EntertainmentName")
    val entertainmentName: String,
    @SerializedName("EntertainmentContent")
    val entertainmentContent:String,
    @SerializedName("EntertainmentStartDate")
    val entertainmentStartDate: String,
    @SerializedName("EntertainmentDueDate")
    val entertainmentDueDate: String,
    @SerializedName("EntertainmentisFree")
    val entertainmentIsFree: Int,
    @SerializedName("EntertainmentPosterUrl")
    val entertainmentPosterUrl: String,
    @SerializedName("EntertainmentTicketUrl")
    val entertainmentTicketUrl: String,
    @SerializedName("EntertainmentCity")
    val entertainmentCity: String,
    @SerializedName("EntertainmentDistrict")
    val entertainmentDistrict: String,
    @SerializedName("EntertainmentVenue")
    val entertainmentVenue: String,
    @SerializedName("EntertainmentCategoryID")
    val entertainmentCategoryID: Int,
    @SerializedName("EntertainmentPerformer")
    val entertainmentPerformer: String
)