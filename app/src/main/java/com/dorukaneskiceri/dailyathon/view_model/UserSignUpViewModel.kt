package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.UserSignUpService
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.HashMap

class UserSignUpViewModel : ViewModel() {

    private var hashMap = HashMap<String, String>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    val myUserSignUp = MutableLiveData<UserResponseMessage>()

    fun postUserSignUp() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val currentTime: Date = Calendar.getInstance().time
            getHashMapItems()

            val response = UserSignUpService().userSignUp(hashMap, currentTime)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        myUserSignUp.value = it
                        println("Kayıt başarılı")
                    }
                } else {
                    println(response.message())
                }
            }
        }
    }

    private fun getHashMapItems() {
        hashMap.put("UserName", "Ali")
        hashMap.put("UserSurname", "Metin")
        hashMap.put("UserEmail", "feyyaz@gmail.com")
        hashMap.put("UserPassword", "ali")
        hashMap.put("UserDate", "1999-10-15")
        hashMap.put("UserProfession", "Mekatronik")
        hashMap.put("UserCity", "Ankara")
    }
}