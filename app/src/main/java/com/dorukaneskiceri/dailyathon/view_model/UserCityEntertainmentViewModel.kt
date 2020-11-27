package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.service.UserCityEntertainmentService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*


class UserCityEntertainmentViewModel: ViewModel(){

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }
    private var arrayListEntertainment = ArrayList<UserEntertainmentModel>()
    var userCityEntertainment = MutableLiveData<UserEntertainmentModel>()

    fun getUserCityEntertainment(token: String, userCity: String, view: View){
        getDataFromAPI(token, userCity, view)
    }

    private fun getDataFromAPI(token: String, userCity: String, view: View) {
        view2 = view
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
                }
            }
        }
    }
}