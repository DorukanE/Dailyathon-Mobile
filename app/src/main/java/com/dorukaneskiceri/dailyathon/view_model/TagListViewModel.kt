package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
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

    fun getTagList(view: View){
        getDataFromAPI(view)
    }

    private fun getDataFromAPI(view: View) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = TagListService().getTagList()
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