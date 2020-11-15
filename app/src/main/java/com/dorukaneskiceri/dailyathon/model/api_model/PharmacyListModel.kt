package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class PharmacyListModel(
    @SerializedName("PharmacyID")
    val pharmacyID: Int,
    @SerializedName("Name")
    val pharmacyName: String,
    @SerializedName("City")
    val pharmacyCity: String,
    @SerializedName("Address")
    val pharmacyAddress: String,
    @SerializedName("Phone")
    val pharmacyPhone: String,
    @SerializedName("Location")
    val pharmacyLocation: String,
    @SerializedName("Dist")
    val pharmacyDist: String,
)