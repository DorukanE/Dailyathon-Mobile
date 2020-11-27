package com.dorukaneskiceri.dailyathon.view_model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukaneskiceri.dailyathon.model.UserListModel
import com.dorukaneskiceri.dailyathon.service.UserListService
import kotlinx.coroutines.*

class UserListViewModel: ViewModel() {
    private var arrayListUsers = ArrayList<UserListModel>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    val myUserList = MutableLiveData<UserListModel>()

    fun getUserList(view: View){
        getDataFromAPI(view)
    }

    private fun getDataFromAPI(view: View) {
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
                    Toast.makeText(view.context, "Lütfen sayfayı yenileyiniz", Toast.LENGTH_SHORT).show()
                }
            }
            if(job!!.isActive){
                job!!.cancel()
            }
        }
    }
}