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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterProfile
import com.dorukaneskiceri.dailyathon.login_signup.LoginActivity
import com.dorukaneskiceri.dailyathon.model.api_model.CategoryListModel
import com.dorukaneskiceri.dailyathon.view_model.CategoryListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.runBlocking

class FragmentProfile : Fragment() {

    private lateinit var viewModelCategory: CategoryListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    var adapter: RecyclerAdapterProfile? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        viewModelCategory = ViewModelProvider(this).get(CategoryListViewModel::class.java)

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

        val name = sharedPreferencesUserName.getString("name", "")
        val surname = sharedPreferencesUserSurname.getString("surname", "")
        textViewUserName.text = name
        textViewUserSurname.text = surname

        val arrayListCategory = ArrayList<CategoryListModel>()
        recyclerViewProfile.layoutManager = LinearLayoutManager(view.context)

        showNavigationBar()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")
        runBlocking {
            getToken(userEmail!!, userPassword!!, sharedPreferencesToken)
        }

        val token = sharedPreferencesToken.getString("token", "")
        listCategories(view.context, arrayListCategory, token!!)

        updateText.setOnClickListener {
            hideNavigationBar()
            val action = FragmentProfileDirections.actionDestinationProfileToFragmentUpdateProfile()
            Navigation.findNavController(it).navigate(action)
        }

        doExitText.setOnClickListener {
            val inflater = LayoutInflater.from(it.context)
            val view = inflater.inflate(R.layout.exit_alert_dialog,null)
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
        sharedPreferencesToken: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, { response ->
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
        })
    }

    private fun listCategories(
        context: Context,
        arrayListCategory: ArrayList<CategoryListModel>,
        token: String
    ) {
        viewModelCategory.getCategories(token)
        viewModelCategory.categoryList.observe(viewLifecycleOwner, { response ->
            println("okundu")
            arrayListCategory.add(response)
            val adapter = RecyclerAdapterProfile(context, arrayListCategory)
            recyclerViewProfile.adapter = adapter
            progressBar2.visibility = View.INVISIBLE
        })
    }

}