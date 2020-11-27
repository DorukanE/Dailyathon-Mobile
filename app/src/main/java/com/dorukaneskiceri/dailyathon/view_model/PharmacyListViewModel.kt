package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.PharmacyListModel
import com.dorukaneskiceri.dailyathon.service.PharmacyListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class PharmacyListViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListPharmacy = ArrayList<PharmacyListModel>()
    val pharmacyList = MutableLiveData<PharmacyListModel>()

    fun getPharmacyList(token: String, city: String, view: View){
        getDataFromAPI(token, city, view)
    }

    private fun getDataFromAPI(token: String, city: String, view: View) {
        view2 = view
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
                }else{
                    println(response.message())
                }
            }
        }
    }
}