package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserTagListModel
import com.dorukaneskiceri.dailyathon.service.UserTagListService
import kotlinx.coroutines.*

class UserTagListViewModel: ViewModel() {

    private var job: Job? = null
    private var arrayListTag = ArrayList<UserTagListModel>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    var userTagList = MutableLiveData<UserTagListModel>()

    fun getUserTags(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserTagListService().getUserTags(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOjEsImlhdCI6MTYwMzIwMDAwMSwiZXhwIjoxNjAzMjAwNzIxfQ.E_6HXdhK4iEeK7hTsBrQZ-1NhYl4AYohUHQKpqfwQxA",
                1
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListTag = it
                        arrayListTag.forEach {
                            userTagList.value = it
                            println("Kullanıcı tagleri okundu")
                        }
                    }
                }else{
                    response.message()
                }
            }
        }
    }
}