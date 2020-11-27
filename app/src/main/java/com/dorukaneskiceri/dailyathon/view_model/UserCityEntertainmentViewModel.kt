package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.service.UserCityEntertainmentService
import kotlinx.coroutines.*


class UserCityEntertainmentViewModel: ViewModel(){

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListEntertainment = ArrayList<UserEntertainmentModel>()
    var userCityEntertainment = MutableLiveData<UserEntertainmentModel>()

    fun getUserCityEntertainment(token: String, userCity: String, view: View){
        getDataFromAPI(token, userCity, view)
    }

    private fun getDataFromAPI(token: String, userCity: String, view: View) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserCityEntertainmentService().getUserCityEntertainment(
                token,
                userCity
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListEntertainment = it
                        arrayListEntertainment.forEach {
                            userCityEntertainment.value = it
                            println("Kullanıcı şehir etkinlikleri okundu")
                        }
                    }
                }else{
                    println(response.message())
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}