package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsNews

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterDailyNews
import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel
import com.dorukaneskiceri.dailyathon.view_model.NewsListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_daily_news.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentDailyNews : Fragment() {

    private lateinit var adapterDailyNewsAll: RecyclerAdapterDailyNews
    private lateinit var viewModelNewsList: NewsListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelNewsList = ViewModelProvider(this).get(NewsListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", Context.MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", Context.MODE_PRIVATE)
        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", Context.MODE_PRIVATE)

        val arrayListDailyNews = ArrayList<NewsListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getDailyNews(arrayListDailyNews, token!!)
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentDailyNewsDirections.actionFragmentDailyNewsToDestinationNews()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        imageView8.setOnClickListener {
            val action = FragmentDailyNewsDirections.actionFragmentDailyNewsToDestinationNews()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getDailyNews(
        arrayListDailyNews: java.util.ArrayList<NewsListModel>,
        token: String
    ) {
        recyclerViewAllNews.layoutManager = LinearLayoutManager(view?.context)
        viewModelNewsList.getNewsList(token)
        viewModelNewsList.newsList.observe(viewLifecycleOwner, { response ->
            arrayListDailyNews.add(response)
            adapterDailyNewsAll = RecyclerAdapterDailyNews(arrayListDailyNews, false)
            recyclerViewAllNews.adapter = adapterDailyNewsAll
            progressBar8.visibility = View.INVISIBLE
        })
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, { response ->
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
        })
    }
}