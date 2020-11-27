package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.CategoryListModel
import com.dorukaneskiceri.dailyathon.service.CategoryListService
import kotlinx.coroutines.*

class CategoryListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListCategory = ArrayList<CategoryListModel>()
    var categoryList = MutableLiveData<CategoryListModel>()

    fun getCategories(token: String, view: View){
        getDataFromAPI(token, view)
    }

    private fun getDataFromAPI(token: String, view: View) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = CategoryListService().getCategories(
                token,
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
                }else{
                    println(response.message())
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}