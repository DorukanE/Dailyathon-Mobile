package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.api_model.CategoryTagModel
import retrofit2.Response
import retrofit2.http.GET

interface CategoryTagGET {

    @GET("category-tag")
    suspend fun getCategoryTag(): Response<ArrayList<CategoryTagModel>>
}