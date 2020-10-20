package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.CategoryTagModel
import com.dorukaneskiceri.dailyathon.model.api_model.TagListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryTagService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CategoryTagGET::class.java)

    suspend fun getCategoryTag(): Response<ArrayList<CategoryTagModel>>{
        return api.getCategoryTag()
    }
}