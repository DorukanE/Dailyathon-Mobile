package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
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

    fun getUserTags(token: String, userID: Int, view: View) {
        getDataFromAPI(token, userID, view)
    }

    private fun getDataFromAPI(token: String, userID: Int, view: View) {
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
                    println(response.message())
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}