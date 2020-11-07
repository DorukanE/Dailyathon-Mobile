package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsAnnouncement

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterAnnouncement
import com.dorukaneskiceri.dailyathon.model.api_model.UserAnnouncementListModel
import com.dorukaneskiceri.dailyathon.view_model.UserAnnouncementListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_announcement.*
import kotlinx.coroutines.runBlocking

class FragmentAnnouncement : Fragment() {

    private lateinit var viewModelUserAnnouncements: UserAnnouncementListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_announcement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserID)
        }

        val token = sharedPreferencesToken.getString("token", "")
        val userID = sharedPreferencesUserID.getInt("userID", 0)
        listAnnouncements(arrayListAnnouncement, token!!, userID)

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
        viewModelUserAnnouncements.getUserAnnouncements(token, userID)
        viewModelUserAnnouncements.announcementList.observe(viewLifecycleOwner, { response ->
            println("duyurular")
            arrayListAnnouncement.add(response)
            val adapter = RecyclerAdapterAnnouncement(arrayListAnnouncement)
            recyclerViewAnnouncement.adapter = adapter
            progressBar4.visibility = View.INVISIBLE
        })
    }

}