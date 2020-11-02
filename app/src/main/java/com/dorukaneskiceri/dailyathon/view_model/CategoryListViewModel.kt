package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.CategoryListModel
import com.dorukaneskiceri.dailyathon.service.CategoryListService
import kotlinx.coroutines.*

class CategoryListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListCategory = ArrayList<CategoryListModel>()
    var categoryList = MutableLiveData<CategoryListModel>()

    fun getCategories(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = CategoryListService().getCategories(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOjEsImlhdCI6MTYwNDMxNzIwMSwiZXhwIjoxNjA0MzE3OTIxfQ.L3w1Qz1vQcVe8G7c4TO03xMXQ0nDgpRGxu5t0sYwL6M"
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListCategory = it
                        arrayListCategory.forEach {
                            categoryList.value = it
                            println("Kategoriler okundu")
                        }
                    }
                }
            }
        }
    }
}