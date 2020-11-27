package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.CryptoListModel
import com.dorukaneskiceri.dailyathon.service.CryptoListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class CryptoListViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListCrypto = ArrayList<CryptoListModel>()
    var cryptoList = MutableLiveData<CryptoListModel>()

    fun getCryptoList(token: String, view: View){
        getDataFromAPI(token, view)
    }

    private fun getDataFromAPI(token: String, view: View) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = CryptoListService().getCryptoList(token)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListCrypto = it
                        arrayListCrypto.forEach {
                            cryptoList.value = it
                            println("Crypto okundu")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}