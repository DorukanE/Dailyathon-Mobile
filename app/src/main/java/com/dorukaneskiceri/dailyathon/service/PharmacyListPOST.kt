package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.PharmacyListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface PharmacyListPOST {

    @FormUrlEncoded
    @POST("pharmacy-list")
    suspend fun getPharmacyList(
        @Header("token") token: String,
        @Field("city") city: String
    ):Response<ArrayList<PharmacyListModel>>
}