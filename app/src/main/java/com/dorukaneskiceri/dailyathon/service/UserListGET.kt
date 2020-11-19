package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.UserListModel
import retrofit2.Response
import retrofit2.http.GET
import kotlin.collections.ArrayList

interface UserListGET {

    @GET("user")
    suspend fun getUserList(): Response<ArrayList<UserListModel>>
}