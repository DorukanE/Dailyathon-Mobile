package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.UserTagDeleteService
import kotlinx.coroutines.*

class UserTagDeleteViewModel : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    val findUser = MutableLiveData<UserResponseMessage>()

    fun getUserList(token: String, userID: Int, tagName: String) {
        getDataFromAPI(token, userID, tagName)
    }

    private fun getDataFromAPI(token: String, userID: Int, tagName: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserTagDeleteService().getUserFind(
                token,
                userID,
                tagName
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        findUser.value = it
                        println("Silme işlemi gerçekleştiriliyor")
                    }
                }else{
                    println(response.message())
                }
            }
//            if (job!!.isActive) {
//                job!!.cancel()
//            }
        }
    }
}