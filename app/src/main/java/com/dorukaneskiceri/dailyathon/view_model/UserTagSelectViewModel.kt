package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.UserTagSelectService
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class UserTagSelectViewModel : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    var selectTags = MutableLiveData<UserResponseMessage>()

    fun saveUserTags(token: String, userID: Int, tagName: String) {
        getDataFromAPI(token, userID, tagName)
    }

    private fun getDataFromAPI(token: String, userID: Int, tagName: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val currentTime: Date = Calendar.getInstance().time
            val inputFormatter =  SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
            val outputFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = inputFormatter.parse(currentTime.toString())
            val regDate = outputFormat.format(date)
            val response = UserTagSelectService().saveUserTags(
                token,
                userID,
                tagName,
                regDate
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        selectTags.value = it
                        println("Kullanıcının etiketleri kaydedildi")
                    }
                } else {
                    println(response.message())
                }
            }
        }
    }
}