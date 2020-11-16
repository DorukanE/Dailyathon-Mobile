package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.StockListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StockListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StockListGET::class.java)

    suspend fun getStockList(token: String): Response<ArrayList<StockListModel>> {
        return api.getStockList(token)
    }
}