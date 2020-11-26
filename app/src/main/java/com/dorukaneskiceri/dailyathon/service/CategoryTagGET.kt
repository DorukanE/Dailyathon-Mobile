package com.dorukaneskiceri.dailyathon.service

import com.dorukaneskiceri.dailyathon.model.CategoryTagModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CategoryTagGET {

    @GET("category-tag")
    suspend fun getCategoryTag(
        @Header("token") token: String
    ): Response<ArrayList<CategoryTagModel>>
}