package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.CurrencyListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyListGET::class.java)

    suspend fun getCurrencyList(token: String): Response<ArrayList<CurrencyListModel>> {
        return api.getCurrencyList(token)
    }
}