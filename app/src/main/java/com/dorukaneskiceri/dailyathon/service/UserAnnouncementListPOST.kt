package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.UserAnnouncementListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAnnouncementListPOST {

    @FormUrlEncoded
    @POST("announcementUserList")
    suspend fun getUserAnnouncements(
        @Header("token") token: String,
        @Field("UserID") userID: Int
    ): Response<ArrayList<UserAnnouncementListModel>>
}