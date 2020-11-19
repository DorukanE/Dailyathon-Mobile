package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.StockListModel
import com.dorukaneskiceri.dailyathon.service.StockListService
import kotlinx.coroutines.*

class StockListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListStock = ArrayList<StockListModel>()
    var stockList = MutableLiveData<StockListModel>()

    fun getStockList(token: String){
        getDataFromAPI(token)
    }

    private fun getDataFromAPI(token: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = StockListService().getStockList(token)
            withContext(Dispatchers.Main){
                response.body()?.let {
                    arrayListStock = it
                    arrayListStock.forEach {
                        stockList.value = it
                        println("Stock okundu")
                    }
                }
            }
        }
    }
}