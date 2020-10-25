package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.service.UserCityEntertainmentService
import com.dorukaneskiceri.dailyathon.service.UserTagEntertainmentService
import kotlinx.coroutines.*


class UserCityEntertainmentViewModel: ViewModel(){

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListEntertainment = ArrayList<UserEntertainmentModel>()
    var userCityEntertainment = MutableLiveData<UserEntertainmentModel>()

    fun getUserCityEntertainment(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserCityEntertainmentService().getUserCityEntertainment(
                "",
                "İzmir"
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
                }
            }
        }
    }
}