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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterEditTags
import com.dorukaneskiceri.dailyathon.model.CategoryTagModel
import com.dorukaneskiceri.dailyathon.model.UserTagListModel
import com.dorukaneskiceri.dailyathon.view_model.CategoryTagViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_edit_tags.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentEditTags : Fragment() {

    private lateinit var viewModelCategoryTag: CategoryTagViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var categoryName: String
    private lateinit var userTags: ArrayList<UserTagListModel>
    private var adapter: RecyclerAdapterEditTags? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_tags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            categoryName = FragmentEditTagsArgs.fromBundle(it).categoryName
            userTags = FragmentEditTagsArgs.fromBundle(it).userTags.arrayListTags
            textView20.text = categoryName
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentEditTagsDirections.actionFragmentEditTagsToFragmentProfileDetail(categoryName)
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelCategoryTag = ViewModelProvider(this).get(CategoryTagViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListEditTags = ArrayList<CategoryTagModel>()
        recyclerViewEditTags.layoutManager = LinearLayoutManager(view.context)

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
            getTags(token!!, arrayListEditTags, view, userTags)
        }


        imageView27.setOnClickListener {
            val action = FragmentEditTagsDirections.actionFragmentEditTagsToFragmentProfileDetail(categoryName)
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getTags(
        token: String,
        arrayListEditTags: java.util.ArrayList<CategoryTagModel>,
        view: View,
        userTags: ArrayList<UserTagListModel>
    ) {
        viewModelCategoryTag.getCategoryTag(token)
        viewModelCategoryTag.categoryTagViewModel.observe(viewLifecycleOwner, {response ->
            if(this.categoryName == response.categoryName){
                arrayListEditTags.add(response)
                adapter = RecyclerAdapterEditTags(arrayListEditTags, view, userTags)
                recyclerViewEditTags.adapter = adapter
                progressBar19.visibility = View.INVISIBLE
            }else{
                progressBar19.visibility = View.INVISIBLE
            }
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