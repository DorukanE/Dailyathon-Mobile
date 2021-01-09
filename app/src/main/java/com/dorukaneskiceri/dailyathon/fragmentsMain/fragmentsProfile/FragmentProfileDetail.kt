package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsProfile

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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterUserTags
import com.dorukaneskiceri.dailyathon.model.UserTagListModel
import com.dorukaneskiceri.dailyathon.model.UserTags
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserTagListViewModel
import kotlinx.android.synthetic.main.fragment_profile_detail.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentProfileDetail : Fragment() {

    private lateinit var viewModelUserTag: UserTagListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private var adapter: RecyclerAdapterUserTags? = null
    private lateinit var categoryName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentProfileDetailDirections.actionFragmentProfileScreenToDestinationProfile()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        arguments?.let {
            categoryName = FragmentProfileDetailArgs.fromBundle(it).categoryName
            textView59.text = categoryName
        }

        viewModelUserTag = ViewModelProvider(this).get(UserTagListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesUserID: SharedPreferences =
            requireActivity().getSharedPreferences("userID", MODE_PRIVATE)
        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListUserTags = ArrayList<UserTagListModel>()
        recyclerViewUserTags.layoutManager = LinearLayoutManager(view.context)

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
            getUserTags(token!!, userID, arrayListUserTags, categoryName)
        }

        imageView19.setOnClickListener {
            val action =
                FragmentProfileDetailDirections.actionFragmentProfileScreenToDestinationProfile()
            Navigation.findNavController(it).navigate(action)
        }

        imageView25.setOnClickListener {
            val userTags = UserTags(arrayListUserTags)
            val action =
                FragmentProfileDetailDirections.actionFragmentProfileDetailToFragmentEditTags(categoryName, userTags)
            Navigation.findNavController(it).navigate(action)
        }
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

    private fun getUserTags(
        token: String,
        userID: Int,
        arrayListUserTags: java.util.ArrayList<UserTagListModel>,
        categoryName: String,
    ) {
        viewModelUserTag.getUserTags(token, userID, requireView())
        viewModelUserTag.userTagList.observe(viewLifecycleOwner, { responseTags ->
            if (categoryName == responseTags.categoryName) {
                arrayListUserTags.add(responseTags)
                adapter = RecyclerAdapterUserTags(arrayListUserTags, categoryName)
                recyclerViewUserTags.adapter = adapter
                progressBar18.visibility = View.INVISIBLE
            } else {
                progressBar18.visibility = View.INVISIBLE
            }
        })
    }
}