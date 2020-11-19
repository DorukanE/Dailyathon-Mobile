package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.TagListModel
import retrofit2.Response
import retrofit2.http.GET

interface TagListGET {

    @GET("tag")
    suspend fun getTagList(): Response<ArrayList<TagListModel>>
}