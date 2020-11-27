package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.PharmacyListModel
import com.dorukaneskiceri.dailyathon.service.PharmacySearchService
import kotlinx.coroutines.*

class PharmacySearchViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListPharmacySearch = ArrayList<PharmacyListModel>()
    val pharmacySearch = MutableLiveData<PharmacyListModel>()

    fun getPharmacySearch(token: String, district: String){
        getDataFromAPI(token, district)
    }

    private fun getDataFromAPI(token: String, district: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = PharmacySearchService().getPharmacySearch(token, district)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListPharmacySearch = it
                        arrayListPharmacySearch.forEach {
                            pharmacySearch.value = it
                            println("Eczane bölgeleri okundu")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}