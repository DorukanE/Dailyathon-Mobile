package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.items.NewsItemsTurkey
import com.dorukaneskiceri.dailyathon.items.NewsItemsWorld
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterNewsPersonal
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterUserEntertainment
import com.dorukaneskiceri.dailyathon.model.api_model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserNewsListModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserNewsListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserTagEntertainmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_chosen.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.runBlocking

class FragmentNews : Fragment() {

    private lateinit var viewModelUserNewsPersonal: UserNewsListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var adapter: RecyclerAdapterNewsPersonal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelUserNewsPersonal = ViewModelProvider(this).get(UserNewsListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", Context.MODE_PRIVATE)
        val sharedPreferencesUserID: SharedPreferences =
            requireActivity().getSharedPreferences("userID", Context.MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", Context.MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", Context.MODE_PRIVATE)

        val arrayListNewsPersonal = ArrayList<UserNewsListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserID)
            val token = sharedPreferencesToken.getString("token", "")
            val userID = sharedPreferencesUserID.getInt("userID", 0)
            getUserNewsPersonal(arrayListNewsPersonal, token!!, userID)
        }
        showNavigationBar()

        getWorldNewsView()
        getTurkeyNewsView()
    }

    private fun getUserNewsPersonal(
        arrayListNewsPersonal: java.util.ArrayList<UserNewsListModel>,
        token: String,
        userID: Int
    ) {
        recyclerViewNewsPersonal.layoutManager = LinearLayoutManager(view?.context)
        viewModelUserNewsPersonal.getUserNews(token, userID)
        viewModelUserNewsPersonal.userNewsList.observe(viewLifecycleOwner, {response ->
            arrayListNewsPersonal.add(response)
            adapter = RecyclerAdapterNewsPersonal(arrayListNewsPersonal)
            recyclerViewNewsPersonal.adapter = adapter
            progressBar7.visibility = View.INVISIBLE
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

    private fun showNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if(bottomNavigationBar.visibility == View.GONE){
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }

    private fun getTurkeyNewsView() {
        recyclerViewNewsTurkey.layoutManager = LinearLayoutManager(view?.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewNewsTurkey.adapter = adapter

        for (i in 1..3){
            adapter.add(NewsItemsTurkey())
        }

        adapter.setOnItemClickListener { item, view ->

        }
    }

    private fun getWorldNewsView(){
        recyclerViewNews.layoutManager = LinearLayoutManager(view?.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewNews.adapter = adapter

        for(i in 1..3){
            adapter.add(NewsItemsWorld())
        }

        adapter.setOnItemClickListener { item, view ->

        }
    }
}