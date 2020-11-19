package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class UserAnnouncementReadService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAnnouncementReadPOST::class.java)

    suspend fun userAnnouncement(token: String, userID: Int, surveyListID: Int, regDate: Date): Response<UserResponseMessage> {
        return api.userAnnouncementRead(token, userID, surveyListID, regDate)
    }
}