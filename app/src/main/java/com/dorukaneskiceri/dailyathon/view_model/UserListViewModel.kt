package com.dorukaneskiceri.dailyathon.view_model

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

    fun getUserList(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
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