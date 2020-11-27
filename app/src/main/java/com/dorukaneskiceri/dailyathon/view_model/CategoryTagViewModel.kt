package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.CategoryTagModel
import com.dorukaneskiceri.dailyathon.service.CategoryTagService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class CategoryTagViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListCategoryTag = ArrayList<CategoryTagModel>()
    var categoryTagViewModel = MutableLiveData<CategoryTagModel>()

    fun getCategoryTag(token: String, view: View){
        getDataFromAPI(token, view)
    }

    private fun getDataFromAPI(token: String, view: View) {
        view2 = view
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