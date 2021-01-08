package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.EntertainmentListModel
import com.dorukaneskiceri.dailyathon.service.EntertainmentListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class EntertainmentListViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListEntertainment = ArrayList<EntertainmentListModel>()
    var entertainmentList = MutableLiveData<EntertainmentListModel>()

    fun getEntertainmentList(view: View, token: String){
        getDataFromAPI(view, token)
    }

    private fun getDataFromAPI(view: View, token: String) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = EntertainmentListService().getEntertainments(token)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListEntertainment = it
                        arrayListEntertainment.forEach {
                            entertainmentList.value = it
                            println("Eğlencelerin okuması başarılı")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}