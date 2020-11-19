package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.ChangePasswordService
import kotlinx.coroutines.*

class ChangePasswordViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    val changePasswordField = MutableLiveData<UserResponseMessage>()

    fun changePassword(){
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = ChangePasswordService().changePassword(
                "Doruk",
                "Eskiceri",
                "doruk@gmail.com",
                "doruk",
                "1999-10-12")
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        changePasswordField.value = it
                        println("Şifre değişimi başarılı")
                    }
                }else{
                    println("Şifre değişimi başarısız")
                }
            }
        }
    }
}