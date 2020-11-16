package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.StockListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface StockListGET {

    @GET("stock")
    suspend fun getStockList(
        @Header("token") token: String
    ): Response<ArrayList<StockListModel>>
}