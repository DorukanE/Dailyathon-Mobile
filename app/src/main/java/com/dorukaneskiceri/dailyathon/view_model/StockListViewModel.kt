package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.StockListModel
import com.dorukaneskiceri.dailyathon.service.StockListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class StockListViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListStock = ArrayList<StockListModel>()
    var stockList = MutableLiveData<StockListModel>()

    fun getStockList(token: String, view: View){
        getDataFromAPI(token, view)
    }

    private fun getDataFromAPI(token: String, view: View) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = StockListService().getStockList(token)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListStock = it
                        arrayListStock.forEach {
                            stockList.value = it
                            println("Stock okundu")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}