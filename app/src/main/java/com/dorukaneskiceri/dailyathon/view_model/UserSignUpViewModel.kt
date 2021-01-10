package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserResponseMessage
import com.dorukaneskiceri.dailyathon.service.UserSignUpService
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class UserSignUpViewModel : ViewModel() {

    private var hashMap = HashMap<String, String>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    val myUserSignUp = MutableLiveData<UserResponseMessage>()

    fun postUserSignUp(
        userName: String,
        userSurname: String,
        userEmail: String,
        userPassword: String,
        userBirth: String,
        userJob: String,
        userCity: String
    ) {
        getDataFromAPI(userName, userSurname, userEmail, userPassword, userBirth, userJob, userCity)
    }

    private fun getDataFromAPI(
        userName: String,
        userSurname: String,
        userEmail: String,
        userPassword: String,
        userBirth: String,
        userJob: String,
        userCity: String
    ) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val regDate = getRegDate()
            getHashMapItems(
                userName,
                userSurname,
                userEmail,
                userPassword,
                userBirth,
                userJob,
                userCity
            )

            val response = UserSignUpService().userSignUp(hashMap, regDate)
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

    private fun getHashMapItems(
        userName: String,
        userSurname: String,
        userEmail: String,
        userPassword: String,
        userBirth: String,
        userJob: String,
        userCity: String
    ) {
        hashMap.put("UserName", userName)
        hashMap.put("UserSurname", userSurname)
        hashMap.put("UserMail", userEmail)
        hashMap.put("UserPassword", userPassword)
        hashMap.put("UserDate", userBirth)
        hashMap.put("UserProfession", userJob)
        hashMap.put("UserCity", userCity)
    }

    private fun getRegDate(): String {
        val currentTime: Date = Calendar.getInstance().time
        val inputFormatter = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = inputFormatter.parse(currentTime.toString())
        return outputFormat.format(date)
    }

}