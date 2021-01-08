package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsEntertainment

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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterCityEntertainment
import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.view_model.UserCityEntertainmentViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_chosen_city.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat

class FragmentChosenCity : Fragment() {

    private lateinit var viewModelUserCityEntertainment: UserCityEntertainmentViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var adapter: RecyclerAdapterCityEntertainment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chosen_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelUserCityEntertainment = ViewModelProvider(this).get(UserCityEntertainmentViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesCity: SharedPreferences =
            requireActivity().getSharedPreferences("userCity", MODE_PRIVATE)
        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListCityEntertainment = ArrayList<UserEntertainmentModel>()
        recyclerViewChosenCity.layoutManager = LinearLayoutManager(view.context)

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")
        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesCity)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userCity = sharedPreferencesCity.getString("city", "")
            getUserCityEntertainment(token!!, userCity!!, arrayListCityEntertainment)
        }
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences,
        sharedPreferencesCity: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, {response ->
            val token = response.token
            val city = response.userInformation.userCity.toLowerCase()
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesCity.edit().putString("city", city).apply()
        })
    }

    private fun getUserCityEntertainment(
        token: String,
        userCity: String,
        arrayListCityEntertainment: ArrayList<UserEntertainmentModel>
    ) {
        viewModelUserCityEntertainment.getUserCityEntertainment(token, userCity, requireView())
        viewModelUserCityEntertainment.userCityEntertainment.observe(viewLifecycleOwner, { response ->
            val startDate = getUserCityStartDate(response)
            val dueDate = getUSerCityDueDate(response)
            arrayListCityEntertainment.add(response)
            adapter = RecyclerAdapterCityEntertainment(arrayListCityEntertainment, startDate, dueDate)
            recyclerViewChosenCity.adapter = adapter
            progressBar6.visibility = View.INVISIBLE
        })
    }

    private fun getUserCityStartDate(response: UserEntertainmentModel): String {
        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.entertainmentStartDate)
        return outputFormat.format(date)
    }

    private fun getUSerCityDueDate(response: UserEntertainmentModel): String {
        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.entertainmentDueDate)
        return outputFormat.format(date)
    }
}