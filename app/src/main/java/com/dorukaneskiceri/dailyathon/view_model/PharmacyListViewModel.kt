package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.PharmacyListModel
import com.dorukaneskiceri.dailyathon.service.PharmacyListService
import kotlinx.coroutines.*

class PharmacyListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }

    private var arrayListPharmacy = ArrayList<PharmacyListModel>()
    val pharmacyList = MutableLiveData<PharmacyListModel>()

    fun getPharmacyList(token: String, city: String){
        getDataFromAPI(token, city)
    }

    private fun getDataFromAPI(token: String, city: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = PharmacyListService().getPharmacyList(token, city)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListPharmacy = it
                        arrayListPharmacy.forEach {
                            pharmacyList.value = it
                            println("Eczaneler okundu")
                        }
                    }
                }
            }
        }
    }
}