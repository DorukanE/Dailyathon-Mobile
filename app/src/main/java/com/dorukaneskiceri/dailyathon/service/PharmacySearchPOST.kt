package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.PharmacyListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface PharmacySearchPOST {

    @FormUrlEncoded
    @POST("pharmacy-list")
    suspend fun getPharmacySearch(
        @Header("token") token:String,
        @Field("dist") district: String
    ): Response<ArrayList<PharmacyListModel>>
}