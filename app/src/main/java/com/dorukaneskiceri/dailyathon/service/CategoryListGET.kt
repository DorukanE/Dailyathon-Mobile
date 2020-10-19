package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.CategoryListModel
import retrofit2.Response
import retrofit2.http.GET

interface CategoryListGET {

    @GET("category")
    suspend fun getCategories(): Response<ArrayList<CategoryListModel>>
}