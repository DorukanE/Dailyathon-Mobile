package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.TagListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TagListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TagListGET::class.java)

    suspend fun getTagList(): Response<ArrayList<TagListModel>>{
        return api.getTagList()
    }
}