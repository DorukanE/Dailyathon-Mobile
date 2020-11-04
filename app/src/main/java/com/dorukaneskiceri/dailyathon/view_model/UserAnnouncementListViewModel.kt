package com.dorukaneskiceri.dailyathon.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserAnnouncementListModel
import com.dorukaneskiceri.dailyathon.service.UserAnnouncementListService
import kotlinx.coroutines.*

class UserAnnouncementListViewModel: ViewModel() {

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    private var arrayListAnnouncement = ArrayList<UserAnnouncementListModel>()
    var announcementList = MutableLiveData<UserAnnouncementListModel>()

    fun getUserAnnouncements(token: String, userID: Int){
        getDataFromAPI(token, userID)
    }

    private fun getDataFromAPI(token: String, userID: Int){
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
                }
            }
        }
    }
}