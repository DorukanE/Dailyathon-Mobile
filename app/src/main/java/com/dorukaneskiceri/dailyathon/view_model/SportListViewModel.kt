package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.SportListModel
import com.dorukaneskiceri.dailyathon.service.SportListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class SportListViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListSport = ArrayList<SportListModel>()
    var sportList = MutableLiveData<SportListModel>()

    fun getSportList(view: View){
        getDataFromAPI(view)
    }

    private fun getDataFromAPI(view: View) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = SportListService().getSports()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListSport = it
                        arrayListSport.forEach {
                            sportList.value = it
                            println("Sporların okuması başarılı")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}