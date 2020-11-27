package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.CategoryListModel
import com.dorukaneskiceri.dailyathon.service.CategoryListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class CategoryListViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListCategory = ArrayList<CategoryListModel>()
    var categoryList = MutableLiveData<CategoryListModel>()

    fun getCategories(token: String, view: View){
        getDataFromAPI(token, view)
    }

    private fun getDataFromAPI(token: String, view: View) {
        view2 = view
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
                }
            }
        }
    }
}