package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.PharmacyListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PharmacyListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PharmacyListPOST::class.java)

    suspend fun getPharmacyList(token: String, city: String): Response<ArrayList<PharmacyListModel>>{
        return api.getPharmacyList(token, city)
    }
}