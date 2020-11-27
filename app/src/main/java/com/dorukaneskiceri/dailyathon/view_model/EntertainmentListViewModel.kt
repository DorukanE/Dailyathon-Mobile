package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
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

    fun getEntertainmentList(view: View){
        getDataFromAPI(view)
    }

    private fun getDataFromAPI(view: View) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = EntertainmentListService().getEntertainments()
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