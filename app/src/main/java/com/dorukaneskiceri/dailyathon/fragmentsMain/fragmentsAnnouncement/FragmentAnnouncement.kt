package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsAnnouncement

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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterAnnouncement
import com.dorukaneskiceri.dailyathon.model.UserAnnouncementListModel
import com.dorukaneskiceri.dailyathon.view_model.UserAnnouncementListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_announcement.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat

class FragmentAnnouncement : Fragment() {

    private lateinit var viewModelUserAnnouncements: UserAnnouncementListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var adapter: RecyclerAdapterAnnouncement

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_announcement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentAnnouncementDirections.actionFragmentAnnouncementToDestinationHome()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelUserAnnouncements = ViewModelProvider(this).get(UserAnnouncementListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences = requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences = requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences = requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)
        val sharedPreferencesUserID: SharedPreferences = requireActivity().getSharedPreferences("userID", MODE_PRIVATE)

        val arrayListAnnouncement = ArrayList<UserAnnouncementListModel>()
        recyclerViewAnnouncement.layoutManager = LinearLayoutManager(view.context)

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")
        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserID)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userID = sharedPreferencesUserID.getInt("userID", 0)
            listAnnouncements(arrayListAnnouncement, token!!, userID)
        }

        backButtonAnnouncement.setOnClickListener {
            val action = FragmentAnnouncementDirections.actionFragmentAnnouncementToDestinationHome()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences,
        sharedPreferencesUserID: SharedPreferences,
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, {response ->
            val userID = response.userInformation.userId
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesUserID.edit().putInt("userID", userID).apply()
        })
    }

    private fun listAnnouncements(
        arrayListAnnouncement: ArrayList<UserAnnouncementListModel>,
        token: String,
        userID: Int
    ) {
        viewModelUserAnnouncements.getUserAnnouncements(token, userID, requireView())
        viewModelUserAnnouncements.announcementList.observe(viewLifecycleOwner, { response ->
            if(response.visible == 1){
                val startDate = getAnnouncementDate(response)
                arrayListAnnouncement.add(response)
                adapter = RecyclerAdapterAnnouncement(arrayListAnnouncement, startDate)
                recyclerViewAnnouncement.adapter = adapter
                progressBar4.visibility = View.INVISIBLE
            }
        })
    }

    private fun getAnnouncementDate(response: UserAnnouncementListModel): String {
        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.announcementDate)
        return outputFormat.format(date)
    }

}