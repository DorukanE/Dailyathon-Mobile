package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.CategoryListModel
import retrofit2.Response
import retrofit2.http.*

interface CategoryListGET {

    @GET("category")
    suspend fun getCategories(
        @Header("token") token: String,
    ): Response<ArrayList<CategoryListModel>>
}