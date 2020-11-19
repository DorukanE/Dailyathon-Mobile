package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.SportListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SportListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SportListGET::class.java)

    suspend fun getSports(): Response<ArrayList<SportListModel>>{
        return api.getSports()
    }
}