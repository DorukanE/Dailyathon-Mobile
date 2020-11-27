package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsPharmacy

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterPSearch
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterPharmacy
import com.dorukaneskiceri.dailyathon.model.PharmacyListModel
import com.dorukaneskiceri.dailyathon.view_model.PharmacyListViewModel
import com.dorukaneskiceri.dailyathon.view_model.PharmacySearchViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_pharmacy.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentPharmacy : Fragment() {

    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelPharmacy: PharmacyListViewModel
    private lateinit var viewModelPharmacySearch: PharmacySearchViewModel
    private lateinit var adapter: RecyclerAdapterPharmacy
    private lateinit var adapterSearch: RecyclerAdapterPSearch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pharmacy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentPharmacyDirections.actionFragmentPharmacyToDestinationDailyathon()
                    Navigation.findNavController(view).navigate(action)
                    showNavigationBar()
                }
            })

        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        viewModelPharmacy = ViewModelProvider(this).get(PharmacyListViewModel::class.java)
        viewModelPharmacySearch = ViewModelProvider(this).get(PharmacySearchViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesUserCity: SharedPreferences =
            requireActivity().getSharedPreferences("userCity", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListPharmacy = ArrayList<PharmacyListModel>()
        val arrayListDistrict = ArrayList<String>()

        recyclerViewPharmacy.layoutManager = LinearLayoutManager(view.context)

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getUser(
                    userEmail!!,
                    userPassword!!,
                    sharedPreferencesToken,
                    sharedPreferencesUserCity
                )
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userCity = sharedPreferencesUserCity.getString("city", "")
            getPharmacyList(arrayListPharmacy, token!!, userCity!!, arrayListDistrict)
        }

        imageButtonBackPharmacy.setOnClickListener {
            showNavigationBar()
            val action = FragmentPharmacyDirections.actionFragmentPharmacyToDestinationDailyathon()
            Navigation.findNavController(it).navigate(action)
        }

        autoCTextDistrict.setOnFocusChangeListener { it, b ->
            val hashSet = HashSet<String>()
            hashSet.addAll(arrayListDistrict)
            arrayListDistrict.clear()
            arrayListDistrict.addAll(hashSet)
            val adapter =
                ArrayAdapter(
                    it.context,
                    R.layout.custom_list_view,
                    R.id.customViewCity,
                    arrayListDistrict
                )
            autoCTextDistrict.setAdapter(adapter)
        }

        buttonPharmacySearch.setOnClickListener {
            if (autoCTextDistrict.text.isNotEmpty()) {
                val arrayListSearch = ArrayList<PharmacyListModel>()
                getToken(userEmail!!, userPassword!!, sharedPreferencesToken)
                val token = sharedPreferencesToken.getString("token", "")
                val district = autoCTextDistrict.text.toString()
                getPharmacySearch(arrayListSearch, token!!, district)
            } else {
                Snackbar.make(it, "Lütfen bir ilçe seçiniz", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun getPharmacySearch(
        arrayListSearch: ArrayList<PharmacyListModel>,
        token: String,
        district: String
    ) {
        viewModelPharmacySearch.getPharmacySearch(token, district, requireView())
        viewModelPharmacySearch.pharmacySearch.observe(viewLifecycleOwner, { response ->
            arrayListSearch.add(response)
            adapterSearch = RecyclerAdapterPSearch(arrayListSearch)
            recyclerViewPharmacy.adapter = adapterSearch
            progressBar11.visibility = View.INVISIBLE
        })
    }

    private fun getPharmacyList(
        arrayListPharmacy: java.util.ArrayList<PharmacyListModel>,
        token: String,
        userCity: String,
        arrayListDistrict: ArrayList<String>
    ) {
        viewModelPharmacy.getPharmacyList(token, userCity, requireView())
        viewModelPharmacy.pharmacyList.observe(viewLifecycleOwner, { response ->
            arrayListPharmacy.add(response)
            arrayListDistrict.add(response.pharmacyDist)
            adapter = RecyclerAdapterPharmacy(arrayListPharmacy)
            recyclerViewPharmacy.adapter = adapter
            progressBar11.visibility = View.INVISIBLE
        })
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences,
        sharedPreferencesUserCity: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, { response ->
            val userCity = response.userInformation.userCity
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesUserCity.edit().putString("city", userCity).apply()
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

    private fun showNavigationBar() {
        val bottomNavigationBar =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if (bottomNavigationBar.visibility == View.GONE) {
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }

}