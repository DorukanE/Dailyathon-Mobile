package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.CategoryListModel
import com.dorukaneskiceri.dailyathon.utils.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryListService {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CategoryListGET::class.java)

    suspend fun getCategories(token: String): Response<ArrayList<CategoryListModel>>{
        return api.getCategories(token)
    }
}