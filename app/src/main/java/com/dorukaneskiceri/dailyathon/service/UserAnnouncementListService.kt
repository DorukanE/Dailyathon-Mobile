package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserAnnouncementListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserAnnouncementListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAnnouncementListPOST::class.java)

    suspend fun getUserAnnouncements(token: String, userID: Int): Response<ArrayList<UserAnnouncementListModel>>{
        return api.getUserAnnouncements(token, userID)
    }
}