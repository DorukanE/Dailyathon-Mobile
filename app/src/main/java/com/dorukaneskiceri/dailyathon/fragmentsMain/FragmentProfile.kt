package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterProfile
import com.dorukaneskiceri.dailyathon.login_signup.LoginActivity
import com.dorukaneskiceri.dailyathon.model.CategoryListModel
import com.dorukaneskiceri.dailyathon.model.UserTagListModel
import com.dorukaneskiceri.dailyathon.view_model.CategoryListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserTagListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentProfile : Fragment() {

    private lateinit var viewModelCategory: CategoryListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelUserTag: UserTagListViewModel
    private lateinit var adapter: RecyclerAdapterProfile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentProfileDirections.actionDestinationProfileToDestinationHome()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        viewModelCategory = ViewModelProvider(this).get(CategoryListViewModel::class.java)
        viewModelUserTag = ViewModelProvider(this).get(UserTagListViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)
        val sharedPreferencesUserName: SharedPreferences =
            requireActivity().getSharedPreferences("userName", MODE_PRIVATE)
        val sharedPreferencesUserSurname: SharedPreferences =
            requireActivity().getSharedPreferences("userSurname", MODE_PRIVATE)
        val sharedPreferencesUserID: SharedPreferences =
            requireActivity().getSharedPreferences("userID", MODE_PRIVATE)

        val name = sharedPreferencesUserName.getString("name", "")
        val surname = sharedPreferencesUserSurname.getString("surname", "")
        textViewUserName.text = name
        textViewUserSurname.text = surname

        val arrayListCategory = ArrayList<CategoryListModel>()
        val arrayListTags = ArrayList<UserTagListModel>()
        recyclerViewProfile.layoutManager = LinearLayoutManager(view.context)

        showNavigationBar()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")
        runBlocking {
            val function = async {
                getToken(
                    userEmail!!,
                    userPassword!!,
                    sharedPreferencesToken,
                    sharedPreferencesUserID
                )
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userID = sharedPreferencesUserID.getInt("userID", 0)
            listCategories(view.context, arrayListCategory, token!!, arrayListTags)
            getUserTags(token, userID, arrayListTags)
        }

        updateText.setOnClickListener {
            hideNavigationBar()
            val action = FragmentProfileDirections.actionDestinationProfileToFragmentUpdateProfile()
            Navigation.findNavController(it).navigate(action)
        }

        doExitText.setOnClickListener {
            val inflater = LayoutInflater.from(it.context)
            val view = inflater.inflate(R.layout.exit_alert_dialog, null)
            val alertDialog = AlertDialog.Builder(it.context)
                .setView(view)
                .create()
            alertDialog.show()

            val exitButton: Button = view.findViewById(R.id.buttonExit)
            val cancelButton: Button = view.findViewById(R.id.buttonCancel)
            exitButton.setOnClickListener {
                val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
                    "com.dorukaneskiceri.dailyathon",
                    MODE_PRIVATE
                )
                sharedPreferences.edit().putBoolean("userIsLogin", false).apply()
                val intent = Intent(it.context, LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
                alertDialog.dismiss()
            }

            cancelButton.setOnClickListener {
                alertDialog.dismiss()
            }

        }
    }

    private fun hideNavigationBar() {
        val navigationBar =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        navigationBar.visibility = View.GONE
    }

    private fun showNavigationBar() {
        val bottomNavigationBar =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if (bottomNavigationBar.visibility == View.GONE) {
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }

    private fun getToken(
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

    private fun listCategories(
        context: Context,
        arrayListCategory: ArrayList<CategoryListModel>,
        token: String,
        arrayListTags: java.util.ArrayList<UserTagListModel>
    ) {
        viewModelCategory.getCategories(token)
        viewModelCategory.categoryList.observe(viewLifecycleOwner, { response ->
            arrayListCategory.add(response)
            adapter = RecyclerAdapterProfile(context, arrayListCategory, arrayListTags)
            recyclerViewProfile.adapter = adapter
            progressBar2.visibility = View.INVISIBLE
        })
    }

    private fun getUserTags(
        token: String,
        userID: Int,
        arrayListTags: java.util.ArrayList<UserTagListModel>,
    ) {
        viewModelUserTag.getUserTags(token, userID)
        viewModelUserTag.userTagList.observe(viewLifecycleOwner, { responseTags ->
            arrayListTags.add(responseTags)
        })
    }
}