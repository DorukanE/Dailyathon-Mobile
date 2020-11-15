package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.PharmacyListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PharmacySearchService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PharmacySearchPOST::class.java)

    suspend fun getPharmacySearch(token: String, district: String): Response<ArrayList<PharmacyListModel>>{
        return api.getPharmacySearch(token, district)
    }
}