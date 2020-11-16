package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.CryptoListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CryptoListGET {

    @GET("cripto")
    suspend fun getCryptoList(
        @Header("token") token: String
    ): Response<ArrayList<CryptoListModel>>
}