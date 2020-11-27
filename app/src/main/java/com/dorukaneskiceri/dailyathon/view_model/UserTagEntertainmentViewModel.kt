package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.service.UserTagEntertainmentService
import kotlinx.coroutines.*

class UserTagEntertainmentViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListEntertainment = ArrayList<UserEntertainmentModel>()
    var userTagEntertainment = MutableLiveData<UserEntertainmentModel>()

    fun getUserTagEntertainment(token: String, userID: Int, view: View){
        getDataFromAPI(token, userID, view)
    }

    private fun getDataFromAPI(token: String, userID: Int, view: View){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserTagEntertainmentService().getUserTagEntertainment(
                token,
                userID
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListEntertainment = it
                        arrayListEntertainment.forEach {
                            userTagEntertainment.value = it
                            println("Kullanıcı etiket etkinlikleri okundu")
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