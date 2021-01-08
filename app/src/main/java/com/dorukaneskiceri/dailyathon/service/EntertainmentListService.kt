package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.EntertainmentListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EntertainmentListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EntertainmentListGET::class.java)

    suspend fun getEntertainments(token: String): Response<ArrayList<EntertainmentListModel>>{
        return api.getEntertainments(token)
    }
}