package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsSport

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
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.FragmentLeagueListBinding
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentDailyathonDirections
import com.dorukaneskiceri.dailyathon.model.LeagueListModel
import com.dorukaneskiceri.dailyathon.view_model.LeagueListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentLeagueList : Fragment() {

    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelLeagueList: LeagueListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentLeagueListDirections.actionFragmentLeagueListToDestinationDailyathon()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelLeagueList = ViewModelProvider(this).get(LeagueListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", Context.MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", Context.MODE_PRIVATE)
        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", Context.MODE_PRIVATE)

        val arrayListLeagues = ArrayList<LeagueListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getToken(userEmail!!, userPassword!!, sharedPreferencesToken)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getLeagueList(arrayListLeagues, token!!)
        }
    }

    private fun getLeagueList(arrayListLeagues: java.util.ArrayList<LeagueListModel>, token: String) {

    }

    private fun getToken(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, {response ->
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
        })
    }
}