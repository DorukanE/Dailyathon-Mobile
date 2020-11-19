package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.CurrencyListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CurrencyListGET {

    @GET("currency")
    suspend fun getCurrencyList(
        @Header("token") token: String
    ): Response<ArrayList<CurrencyListModel>>
}