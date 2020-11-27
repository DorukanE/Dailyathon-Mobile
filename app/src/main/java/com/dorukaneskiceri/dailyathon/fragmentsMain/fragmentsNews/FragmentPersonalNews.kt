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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterPersonalNews
import com.dorukaneskiceri.dailyathon.model.NewsListModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserNewsListViewModel
import kotlinx.android.synthetic.main.fragment_personal_news.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat

class FragmentPersonalNews : Fragment() {

    private lateinit var adapterAllPersonalNews: RecyclerAdapterPersonalNews
    private lateinit var viewModelUserNewsPersonal: UserNewsListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentPersonalNewsDirections.actionFragmentPersonalNewsToDestinationNews()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        viewModelUserNewsPersonal = ViewModelProvider(this).get(UserNewsListViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", Context.MODE_PRIVATE)
        val sharedPreferencesUserID: SharedPreferences =
            requireActivity().getSharedPreferences("userID", Context.MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", Context.MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", Context.MODE_PRIVATE)

        val arrayListNewsPersonal = ArrayList<NewsListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getUser(
                    userEmail!!,
                    userPassword!!,
                    sharedPreferencesToken,
                    sharedPreferencesUserID
                )
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userID = sharedPreferencesUserID.getInt("userID", 0)
            getUserNewsPersonal(arrayListNewsPersonal, token!!, userID)
        }

        imageView9.setOnClickListener {
            val action = FragmentPersonalNewsDirections.actionFragmentPersonalNewsToDestinationNews()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getUserNewsPersonal(
        arrayListNewsPersonal: java.util.ArrayList<NewsListModel>,
        token: String,
        userID: Int
    ) {
        recyclerViewAllPersonalNews.layoutManager = LinearLayoutManager(view?.context)
        viewModelUserNewsPersonal.getUserNews(token, userID, requireView())
        viewModelUserNewsPersonal.userNewsList.observe(viewLifecycleOwner, { response ->
            val newsDate = getNewsDate(response)
            arrayListNewsPersonal.add(response)
            adapterAllPersonalNews = RecyclerAdapterPersonalNews(arrayListNewsPersonal, false, newsDate)
            recyclerViewAllPersonalNews.adapter = adapterAllPersonalNews
            progressBar9.visibility = View.INVISIBLE
        })
    }

    private fun getNewsDate(response: NewsListModel): String {
        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.date)
        return outputFormat.format(date)
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences,
        sharedPreferencesUserID: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, { response ->
            val userID = response.userInformation.userId
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesUserID.edit().putInt("userID", userID).apply()
        })
    }
}