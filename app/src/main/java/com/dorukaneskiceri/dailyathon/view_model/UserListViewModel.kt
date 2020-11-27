package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserListModel
import com.dorukaneskiceri.dailyathon.service.UserListService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class UserListViewModel: ViewModel() {
    private var arrayListUsers = ArrayList<UserListModel>()
    private var job: Job? = null
    private lateinit var view2: View
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        Snackbar.make(view2,"Lütfen sayfayı yenileyiniz", Snackbar.LENGTH_LONG).show()
    }
    val myUserList = MutableLiveData<UserListModel>()

    fun getUserList(view: View){
        getDataFromAPI(view)
    }

    private fun getDataFromAPI(view: View) {
        view2 = view
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = UserListService().getUserList()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayListUsers = it
                        arrayListUsers.forEach {
                            myUserList.value = it
                            println("Okuma başarılı")
                        }
                    }
                }else{
                    println(response.message())
                }
            }
            if(job!!.isActive){
                job!!.cancel()
            }
        }
    }
}