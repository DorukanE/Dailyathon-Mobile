package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserCityEntertainmentService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserCityEntertainmentPOST::class.java)

    suspend fun getUserCityEntertainment(token: String, userCity: String): Response<ArrayList<UserEntertainmentModel>> {
        return api.getUserCityEntertainment(token, userCity)
    }
}