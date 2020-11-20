package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsSurvey

import android.content.Context
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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterSurveys
import com.dorukaneskiceri.dailyathon.model.UserSurveyListModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserSurveyListViewModel
import kotlinx.android.synthetic.main.fragment_survey.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class FragmentSurvey : Fragment() {

    private lateinit var viewModelUserSurveyList: UserSurveyListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var adapter: RecyclerAdapterSurveys

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentSurveyDirections.actionFragmentSurveyToDestinationHome()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelUserSurveyList = ViewModelProvider(this).get(UserSurveyListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences = requireActivity().getSharedPreferences("userToken", MODE_PRIVATE
        )
        val sharedPreferencesEmail: SharedPreferences = requireActivity().getSharedPreferences("userEmail",
            MODE_PRIVATE
        )
        val sharedPreferencesPassword: SharedPreferences = requireActivity().getSharedPreferences("userPassword",
            MODE_PRIVATE
        )
        val sharedPreferencesUserID: SharedPreferences = requireActivity().getSharedPreferences("userID",
            MODE_PRIVATE
        )

        val arrayListSurvey = ArrayList<UserSurveyListModel>()
        recyclerViewSurveys.layoutManager = LinearLayoutManager(view.context)

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")
        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserID)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userID = sharedPreferencesUserID.getInt("userID", 0)
            listSurveys(arrayListSurvey, token!!, userID)
        }

        imageView18.setOnClickListener {
            val action = FragmentSurveyDirections.actionFragmentSurveyToDestinationHome()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun listSurveys(
        arrayListSurvey: java.util.ArrayList<UserSurveyListModel>,
        token: String,
        userID: Int
    ) {
        viewModelUserSurveyList.getUserTags(token, userID)
        viewModelUserSurveyList.userSurveyList.observe(viewLifecycleOwner, { response ->
            if(response.surveyVisible == 1){
                val startDate = getSurveyStartDate(response)
                val dueDate = getSurveyDueDate(response)
                arrayListSurvey.add(response)
                adapter = RecyclerAdapterSurveys(arrayListSurvey, startDate, dueDate)
                recyclerViewSurveys.adapter = adapter
                progressBar10.visibility = View.INVISIBLE
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

    private fun getSurveyStartDate(response: UserSurveyListModel): String {
        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.surveyStartDate)
        return outputFormat.format(date)
    }

    private fun getSurveyDueDate(response: UserSurveyListModel): String {
        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.surveyStartDate)
        return outputFormat.format(date)
    }

}