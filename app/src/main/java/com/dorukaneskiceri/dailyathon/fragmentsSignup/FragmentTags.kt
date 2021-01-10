package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterTagsSignUp
import com.dorukaneskiceri.dailyathon.model.TagListModel
import com.dorukaneskiceri.dailyathon.model.TagsSignUpArgs
import com.dorukaneskiceri.dailyathon.view_model.TagListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_tags.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentTags : Fragment() {

    private lateinit var viewModelTagList: TagListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private var adapter: RecyclerAdapterTagsSignUp? = null
    private lateinit var userName: String
    private lateinit var userSurname: String
    private lateinit var userBirth: String
    private lateinit var userJob: String
    private lateinit var userCity: String
    private lateinit var userEmail: String
    private lateinit var userPassword: String

    fun getTagsSignUp(
        arrayListSelected: ArrayList<String>,
        it: View,
        userName: String,
        userSurname: String,
        userBirth: String,
        userJob: String,
        userCity: String,
        userEmail: String,
        userPassword: String
    ) {
        if (arrayListSelected.isNullOrEmpty()) {
            Toast.makeText(
                it.context,
                "Lütfen İlgilendiğiniz Etiketleri Seçiniz.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val finalTags = TagsSignUpArgs(arrayListSelected)
            val action = FragmentTagsDirections.actionFragmentTagsToFragmentFinal(
                userName,
                userSurname,
                userBirth,
                userJob,
                userCity,
                userEmail,
                userPassword,
                finalTags
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            userName = FragmentTagsArgs.fromBundle(it).userName
            userSurname = FragmentTagsArgs.fromBundle(it).userSurname
            userBirth = FragmentTagsArgs.fromBundle(it).userBirth
            userJob = FragmentTagsArgs.fromBundle(it).userJob
            userCity = FragmentTagsArgs.fromBundle(it).userCity
            userEmail = FragmentTagsArgs.fromBundle(it).userEmail
            userPassword = FragmentTagsArgs.fromBundle(it).userPassword
        }

        viewModelTagList = ViewModelProvider(this).get(TagListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)
        val sharedPreferencesToken: SharedPreferences = requireActivity().getSharedPreferences(
            "userToken",
            MODE_PRIVATE
        )

        val arrayListTags = ArrayList<TagListModel>()
        recyclerViewTags.layoutManager = LinearLayoutManager(view.context)

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")
        runBlocking {
            val function = async {
                getToken(
                    userEmail!!,
                    userPassword!!,
                    sharedPreferencesToken
                )
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getTagsSignUp(token!!, arrayListTags, view)
        }
    }

    private fun getTagsSignUp(
        token: String,
        arrayListTags: java.util.ArrayList<TagListModel>,
        view: View
    ) {
        viewModelTagList.getTagList(view, token)
        viewModelTagList.tagListViewModel.observe(viewLifecycleOwner, { response ->
            arrayListTags.add(response)
            adapter = RecyclerAdapterTagsSignUp(
                arrayListTags,
                view,
                userName,
                userSurname,
                userBirth,
                userJob,
                userCity,
                userEmail,
                userPassword
            )
            recyclerViewTags.adapter = adapter
            progressBar21.visibility = View.INVISIBLE
        })
    }

    private fun getToken(
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