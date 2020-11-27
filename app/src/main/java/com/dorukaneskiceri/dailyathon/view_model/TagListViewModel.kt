package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.TagListModel
import com.dorukaneskiceri.dailyathon.service.TagListService
import kotlinx.coroutines.*

class TagListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListTag = ArrayList<TagListModel>()
    var tagListViewModel = MutableLiveData<TagListModel>()

    fun getTagList(view: View){
        getDataFromAPI(view)
    }

    private fun getDataFromAPI(view: View) {
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
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}