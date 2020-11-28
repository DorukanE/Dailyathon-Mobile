package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserAnnouncementListModel
import com.dorukaneskiceri.dailyathon.service.UserAnnouncementListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class UserAnnouncementListViewModel: ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    private var arrayListAnnouncement = ArrayList<UserAnnouncementListModel>()
    var announcementList = MutableLiveData<UserAnnouncementListModel>()

    fun getUserAnnouncements(token: String, userID: Int, view: View){
        getDataFromAPI(token, userID, view)
    }

    private fun getDataFromAPI(token: String, userID: Int, view: View){
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserAnnouncementListService().getUserAnnouncements(
                token,
                userID
            )
            withContext(Dispatchers.Main + exceptionHandler){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListAnnouncement = it
                        arrayListAnnouncement.forEach {
                            announcementList.value = it
                            println("Kullanıcı duyuruları okundu")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
        }
    }
}