package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.CurrencyListModel
import com.dorukaneskiceri.dailyathon.service.CurrencyListService
import kotlinx.coroutines.*

class CurrencyListViewModel : ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListCurrency = ArrayList<CurrencyListModel>()
    var currencyList = MutableLiveData<CurrencyListModel>()

    fun getCurrencyList(token: String){
        getDataFromAPI(token)
    }

    private fun getDataFromAPI(token: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = CurrencyListService().getCurrencyList(token)
            withContext(Dispatchers.Main){
                response.body()?.let {
                    arrayListCurrency = it
                    arrayListCurrency.forEach {
                        currencyList.value = it
                        println("Currency okundu")
                    }
                }
            }
        }
    }
}