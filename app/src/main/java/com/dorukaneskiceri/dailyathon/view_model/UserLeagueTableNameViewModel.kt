package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserLeagueTableNameModel
import com.dorukaneskiceri.dailyathon.service.UserLeagueTableNameService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class UserLeagueTableNameViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }
    private var arrayListTableName = ArrayList<UserLeagueTableNameModel>()
    var leagueTableNames = MutableLiveData<UserLeagueTableNameModel>()

    fun getUserLeagueTableNames(
        token: String,
        userID: Int,
        view: View,
        progressBar17: ContentLoadingProgressBar
    ){
        getDataFromAPI(token, userID, view, progressBar17)
    }

    private fun getDataFromAPI(
        token: String,
        userID: Int,
        view: View,
        progressBar17: ContentLoadingProgressBar
    ){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            view2 = view
            val response = UserLeagueTableNameService().getUserLeagueTableNames(
                token,
                userID
            )
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListTableName = it
                        if(arrayListTableName.isNullOrEmpty()){
                            println("Dizi boş")
                            Snackbar.make(view, "Aktif spor etiketi bulunamadı, Profil ekranından etiketlerinizi düzenleyebilirsiniz.", Snackbar.LENGTH_LONG).show()
                            progressBar17.visibility = View.INVISIBLE
                        }else{
                            arrayListTableName.forEach {
                                leagueTableNames.value = it
                                println("Kullanıcı lig tabloları? okundu")
                            }
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}