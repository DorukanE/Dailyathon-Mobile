package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserAnnouncementListModel
import com.dorukaneskiceri.dailyathon.service.UserAnnouncementListService
import kotlinx.coroutines.*

class UserAnnouncementListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListAnnouncement = ArrayList<UserAnnouncementListModel>()
    var announcementList = MutableLiveData<UserAnnouncementListModel>()

    fun getUserAnnouncements(token: String, userID: Int, view: View){
        getDataFromAPI(token, userID, view)
    }

    private fun getDataFromAPI(token: String, userID: Int, view: View){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserAnnouncementListService().getUserAnnouncements(
                token,
                userID
            )
            withContext(Dispatchers.Main){
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
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}