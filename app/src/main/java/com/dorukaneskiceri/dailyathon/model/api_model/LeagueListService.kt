package com.dorukaneskiceri.dailyathon.model.api_model

import com.dorukaneskiceri.dailyathon.service.LeagueListGET
import com.dorukaneskiceri.dailyathon.service.SportListGET
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LeagueListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LeagueListGET::class.java)

    suspend fun getLeagues(): Response<ArrayList<LeagueListModel>> {
        return api.getLeagues()
    }
}