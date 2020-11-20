package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsSport

import android.content.Context.MODE_PRIVATE
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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterUserLeagues
import com.dorukaneskiceri.dailyathon.model.UserLeagueTableNameModel
import com.dorukaneskiceri.dailyathon.view_model.UserLeagueTableNameViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.fragment_user_sport.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentUserSport : Fragment() {

    private lateinit var viewModelUserLeagueTableNames: UserLeagueTableNameViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private var adapter: RecyclerAdapterUserLeagues? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_sport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentUserSportDirections.actionFragmentUserSportToDestinationHome()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelUserLeagueTableNames = ViewModelProvider(this).get(UserLeagueTableNameViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)
        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesUserID: SharedPreferences =
            requireActivity().getSharedPreferences("userID", MODE_PRIVATE)

        recyclerViewUserSports.layoutManager = LinearLayoutManager(view.context)
        val arrayListUserLeagues = ArrayList<UserLeagueTableNameModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserID)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userID = sharedPreferencesUserID.getInt("userID", 0)
            getUserLeagueList(arrayListUserLeagues, token!!, userID, view)
        }

        imageView22.setOnClickListener {
            val action = FragmentUserSportDirections.actionFragmentUserSportToDestinationHome()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getUserLeagueList(
        arrayListUserLeagues: java.util.ArrayList<UserLeagueTableNameModel>,
        token: String,
        userID: Int,
        view: View
    ) {
        viewModelUserLeagueTableNames.getUserLeagueTableNames(token, userID, view, progressBar17)
        viewModelUserLeagueTableNames.leagueTableNames.observe(viewLifecycleOwner, {response ->
            if(response.leagueTableName.isNotEmpty()){
                arrayListUserLeagues.add(response)
                adapter = RecyclerAdapterUserLeagues(arrayListUserLeagues)
                recyclerViewUserSports.adapter = adapter
                progressBar17.visibility = View.INVISIBLE
            }
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