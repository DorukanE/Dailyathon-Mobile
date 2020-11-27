package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.CategoryTagModel
import com.dorukaneskiceri.dailyathon.service.CategoryTagService
import kotlinx.coroutines.*

class CategoryTagViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListCategoryTag = ArrayList<CategoryTagModel>()
    var categoryTagViewModel = MutableLiveData<CategoryTagModel>()

    fun getCategoryTag(token: String){
        getDataFromAPI(token)
    }

    private fun getDataFromAPI(token: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = CategoryTagService().getCategoryTag(token)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListCategoryTag = it
                        arrayListCategoryTag.forEach { category ->
                            categoryTagViewModel.value = category
                            println("Kategori Tag okuması başarılı")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}