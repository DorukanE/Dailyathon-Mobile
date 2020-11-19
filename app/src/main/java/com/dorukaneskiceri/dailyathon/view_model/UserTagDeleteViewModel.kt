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

    fun getUserList() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserTagDeleteService().getUserFind(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOjExLCJpYXQiOjE2MDMxOTg0NzEsImV4cCI6MTYwMzE5OTE5MX0.L3MoQEsSiCN0zHcKoh4JJedTR72M1ZrcLR3_oWxu2PQ",
                11,
                "Spor"
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