package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserSurveyListModel
import com.dorukaneskiceri.dailyathon.service.UserSurveyListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class UserSurveyListViewModel : ViewModel() {

    private var job: Job? = null
    private lateinit var view2: View
    private var arrayListSurvey = ArrayList<UserSurveyListModel>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }

    var userSurveyList = MutableLiveData<UserSurveyListModel>()

    fun getUserTags(token: String, userID: Int, view: View) {
        getDataFromAPI(token, userID, view)
    }

    private fun getDataFromAPI(token: String, userID: Int, view: View) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserSurveyListService().getUserSurveys(
                token,
                userID
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        arrayListSurvey = it
                        arrayListSurvey.forEach {
                            userSurveyList.value = it
                            println("Kullanıcı anketleri okundu")
                        }
                    }
                } else {
                    response.message()
                }
            }
        }
    }
}