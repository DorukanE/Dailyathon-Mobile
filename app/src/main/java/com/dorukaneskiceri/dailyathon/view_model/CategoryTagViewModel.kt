package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.CategoryTagModel
import com.dorukaneskiceri.dailyathon.model.api_model.TagListModel
import com.dorukaneskiceri.dailyathon.service.CategoryTagService
import com.dorukaneskiceri.dailyathon.service.TagListService
import kotlinx.coroutines.*

class CategoryTagViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListCategoryTag = ArrayList<CategoryTagModel>()
    var categoryTagViewModel = MutableLiveData<CategoryTagModel>()

    fun getCategoryTag(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = CategoryTagService().getCategoryTag()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListCategoryTag = it
                        arrayListCategoryTag.forEach {
                            categoryTagViewModel.value = it
                            println("Kategori Tag okuması başarılı")
                        }
                    }
                }
            }
        }
    }
}