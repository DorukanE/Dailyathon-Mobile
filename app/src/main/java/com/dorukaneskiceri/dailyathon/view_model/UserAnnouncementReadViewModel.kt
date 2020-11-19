package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.UserAnnouncementReadService
import kotlinx.coroutines.*
import java.util.*

class UserAnnouncementReadViewModel : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    var announcementRead = MutableLiveData<UserResponseMessage>()

    fun getUserAnnouncementRead() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val currentTime: Date = Calendar.getInstance().time
            val response = UserAnnouncementReadService().userAnnouncement(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOjEsImlhdCI6MTYwMzIwNzkxNCwiZXhwIjoxNjAzMjA4NjM0fQ.bnMtb-jTA-Ahj7FyrqXxMBBaPjqt1M3_nimTx_WfOSs",
                1,
                21,
                currentTime
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        announcementRead.value = it
                        println("Kullanıcının duyuru okuma durumu kaydedildi")
                    }
                } else {
                    println(response.message())
                }
            }
        }
    }
}