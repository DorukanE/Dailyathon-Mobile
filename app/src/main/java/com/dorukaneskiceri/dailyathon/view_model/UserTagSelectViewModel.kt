package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.UserTagSelectService
import kotlinx.coroutines.*
import java.util.*

class UserTagSelectViewModel : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    var selectTags = MutableLiveData<UserResponseMessage>()

    fun saveUserTags(view: View) {
        getDataFromAPI(view)
    }

    private fun getDataFromAPI(view: View) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val currentTime: Date = Calendar.getInstance().time
            val response = UserTagSelectService().saveUserTags(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOjExLCJpYXQiOjE2MDM0NDkxNTQsImV4cCI6MTYwMzQ0OTg3NH0.z5fwex-RvywzLdpdUs_7Dm4AB81pgCi7dI8EmIN47tQ",
                1,
                "Spor",
                currentTime
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        selectTags.value = it
                        println("Kullanıcının etiketleri kaydedildi")
                    }
                } else {
                    println(response.message())
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}