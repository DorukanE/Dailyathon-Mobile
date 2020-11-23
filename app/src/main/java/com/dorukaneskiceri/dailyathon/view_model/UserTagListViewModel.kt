package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserTagListModel
import com.dorukaneskiceri.dailyathon.service.UserTagListService
import kotlinx.coroutines.*

class UserTagListViewModel : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListTag = ArrayList<UserTagListModel>()
    var userTagList = MutableLiveData<UserTagListModel>()

    fun getUserTags(token: String, userID: Int) {
        getDataFromAPI(token, userID)
    }

    private fun getDataFromAPI(token: String, userID: Int) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserTagListService().getUserTags(
                token,
                userID
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        arrayListTag = it
                        arrayListTag.forEach {
                            userTagList.value = it
                            println("Kullanıcı tagleri okundu")
                        }
                    }
                } else {
                    response.message()
                }
            }
        }
    }
}