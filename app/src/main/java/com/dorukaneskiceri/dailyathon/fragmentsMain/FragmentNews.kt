package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterDailyNews
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterNewsPersonal
import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserNewsListModel
import com.dorukaneskiceri.dailyathon.view_model.NewsListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserNewsListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentNews : Fragment() {

    private lateinit var viewModelUserNewsPersonal: UserNewsListViewModel
    private lateinit var viewModelNewsList: NewsListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var adapterPersonalNews: RecyclerAdapterNewsPersonal
    private lateinit var adapterDailyNews: RecyclerAdapterDailyNews

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelNewsList = ViewModelProvider(this).get(NewsListViewModel::class.java)
        viewModelUserNewsPersonal = ViewModelProvider(this).get(UserNewsListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesUserID: SharedPreferences =
            requireActivity().getSharedPreferences("userID", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListNewsPersonal = ArrayList<UserNewsListModel>()
        val arrayListDailyNews = ArrayList<NewsListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserID)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userID = sharedPreferencesUserID.getInt("userID", 0)
            getDailyNews(arrayListDailyNews, token!!)
            getUserNewsPersonal(arrayListNewsPersonal, token, userID)
        }
        showNavigationBar()

    }

    private fun showNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if(bottomNavigationBar.visibility == View.GONE){
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }

    private fun getDailyNews(arrayListDailyNews: java.util.ArrayList<NewsListModel>, token: String) {
        recyclerViewNews.layoutManager = LinearLayoutManager(view?.context)
        viewModelNewsList.getNewsList(token)
        var count = 0
        viewModelNewsList.newsList.observe(viewLifecycleOwner, {response ->
            arrayListDailyNews.add(response)
            adapterDailyNews = RecyclerAdapterDailyNews(arrayListDailyNews)
            recyclerViewNews.adapter = adapterDailyNews
            if(count == 1){
                arrayListDailyNews.clear()
            }
            count+=1
        })
    }

    private fun getUserNewsPersonal(
        arrayListNewsPersonal: java.util.ArrayList<UserNewsListModel>,
        token: String,
        userID: Int
    ) {
        recyclerViewNewsPersonal.layoutManager = LinearLayoutManager(view?.context)
        viewModelUserNewsPersonal.getUserNews(token, userID)
        var count = 0
        viewModelUserNewsPersonal.userNewsList.observe(viewLifecycleOwner, {response ->
            arrayListNewsPersonal.add(response)
            adapterPersonalNews = RecyclerAdapterNewsPersonal(arrayListNewsPersonal)
            recyclerViewNewsPersonal.adapter = adapterPersonalNews
            progressBar7.visibility = View.INVISIBLE
            if(count == 1){
                arrayListNewsPersonal.clear()
            }
            count+=1
        })
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences,
        sharedPreferencesUserID: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, {response ->
            val userID = response.userInformation.userId
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesUserID.edit().putInt("userID", userID).apply()
        })
    }


}