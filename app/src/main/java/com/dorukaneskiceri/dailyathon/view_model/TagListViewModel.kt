package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.TagListModel
import com.dorukaneskiceri.dailyathon.service.TagListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class TagListViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListTag = ArrayList<TagListModel>()
    var tagListViewModel = MutableLiveData<TagListModel>()

    fun getTagList(view: View, token: String){
        getDataFromAPI(view, token)
    }

    private fun getDataFromAPI(view: View, token: String) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = TagListService().getTagList(token)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListTag = it
                        arrayListTag.forEach {
                            tagListViewModel.value = it
                            println("Tag okuması başarılı")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}