package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserTagEntertainmentService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserTagEntertainmentPOST::class.java)

    suspend fun getUserTagEntertainment(token: String, userID: Int): Response<ArrayList<UserEntertainmentModel>> {
        return api.getUserTagEntertainment(token, userID)
    }
}