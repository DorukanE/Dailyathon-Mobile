package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsFun

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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterUserEntertainment
import com.dorukaneskiceri.dailyathon.model.api_model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserTagEntertainmentViewModel
import kotlinx.android.synthetic.main.fragment_chosen.*
import kotlinx.coroutines.runBlocking

class FragmentChosen : Fragment() {

    private lateinit var viewModelUserTagEntertainment: UserTagEntertainmentViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var adapter: RecyclerAdapterUserEntertainment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chosen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelUserTagEntertainment = ViewModelProvider(this).get(UserTagEntertainmentViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesUserID: SharedPreferences =
            requireActivity().getSharedPreferences("userID", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListUserEntertainment = ArrayList<UserEntertainmentModel>()
        recyclerViewChosen.layoutManager = LinearLayoutManager(view.context)

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserID)
        }

        val token = sharedPreferencesToken.getString("token", "")
        val userID = sharedPreferencesUserID.getInt("userID", 0)
        getUserTagEntertainment(arrayListUserEntertainment, token!!, userID)
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences,
        sharedPreferencesUserID: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLogin.observe(viewLifecycleOwner, {response ->
            val userID = response.userInformation.userId
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesUserID.edit().putInt("userID", userID).apply()
        })
    }

    private fun getUserTagEntertainment(
        arrayListUserEntertainment: ArrayList<UserEntertainmentModel>,
        token: String,
        userID: Int
    ) {
        viewModelUserTagEntertainment.getUserTagEntertainment(token, userID)
        viewModelUserTagEntertainment.userTagEntertainment.observe(viewLifecycleOwner, { response ->

            arrayListUserEntertainment.add(response)
            adapter = RecyclerAdapterUserEntertainment(arrayListUserEntertainment)
            recyclerViewChosen.adapter = adapter
            progressBar5.visibility = View.INVISIBLE
        })
    }

}