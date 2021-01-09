package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.TagListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TagListGET {

    @GET("tag")
    suspend fun getTagList(
        @Header("token") token: String
    ): Response<ArrayList<TagListModel>>
}