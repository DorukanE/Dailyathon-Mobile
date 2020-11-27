package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.PharmacyListModel
import com.dorukaneskiceri.dailyathon.service.PharmacySearchService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class PharmacySearchViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListPharmacySearch = ArrayList<PharmacyListModel>()
    val pharmacySearch = MutableLiveData<PharmacyListModel>()

    fun getPharmacySearch(token: String, district: String, view: View){
        getDataFromAPI(token, district, view)
    }

    private fun getDataFromAPI(token: String, district: String, view: View) {
        view2 = view
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