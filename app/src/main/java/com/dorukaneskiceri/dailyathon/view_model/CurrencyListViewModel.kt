package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.CurrencyListModel
import com.dorukaneskiceri.dailyathon.service.CurrencyListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class CurrencyListViewModel : ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListCurrency = ArrayList<CurrencyListModel>()
    var currencyList = MutableLiveData<CurrencyListModel>()

    fun getCurrencyList(token: String, view: View){
        getDataFromAPI(token, view)
    }

    private fun getDataFromAPI(token: String, view: View) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = CurrencyListService().getCurrencyList(token)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListCurrency = it
                        arrayListCurrency.forEach {
                            currencyList.value = it
                            println("Currency okundu")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}