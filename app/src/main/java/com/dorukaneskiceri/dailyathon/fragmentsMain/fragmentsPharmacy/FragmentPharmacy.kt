package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsPharmacy

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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterPharmacy
import com.dorukaneskiceri.dailyathon.model.api_model.PharmacyListModel
import com.dorukaneskiceri.dailyathon.view_model.PharmacyListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_pharmacy.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentPharmacy : Fragment() {

    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelPharmacy: PharmacyListViewModel
    private lateinit var adapter: RecyclerAdapterPharmacy

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

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesUserCity: SharedPreferences =
            requireActivity().getSharedPreferences("userCity", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListPharmacy = ArrayList<PharmacyListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserCity)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userCity = sharedPreferencesUserCity.getString("city", "")
            getPharmacyList(arrayListPharmacy, token!!, userCity!!)
        }

        var number: String? = ""

        imageButtonBackPharmacy.setOnClickListener {
            showNavigationBar()
            val action = FragmentPharmacyDirections.actionFragmentPharmacyToDestinationDailyathon()
            Navigation.findNavController(it).navigate(action)
        }

//        adapter.setOnItemClickListener { item, view ->
//            val dialog = BottomSheetDialog(view.context)
//            val view = layoutInflater.inflate(R.layout.dialog_layout,null)
//            dialog.setContentView(view)
//            dialog.show()
//
//            val textViewCancel: TextView = view.findViewById(R.id.textViewCancel)
//            val textViewMakeCall: TextView = view.findViewById(R.id.textViewMakeCall)
//            val textViewFind: TextView = view.findViewById(R.id.textViewFind)
//
//            textViewCancel.setOnClickListener {
//                dialog.dismiss()
//            }
//            textViewFind.setOnClickListener {
//                val intent = Intent(it.context, MapsActivityPharmacy::class.java)
//                startActivity(intent)
//            }
//            textViewMakeCall.setOnClickListener {
//                number = "+905319409022"
//                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
//                startActivity(intent)
//            }
//        }
    }

    private fun getPharmacyList(
        arrayListPharmacy: java.util.ArrayList<PharmacyListModel>,
        token: String,
        userCity: String
    ) {
        recyclerViewPharmacy.layoutManager = LinearLayoutManager(view?.context)
        viewModelPharmacy.getPharmacyList(token, userCity)
        viewModelPharmacy.pharmacyList.observe(viewLifecycleOwner, {response ->
            arrayListPharmacy.add(response)
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
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, {response ->
            val userCity = response.userInformation.userCity
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesUserCity.edit().putString("city", userCity).apply()
        })
    }

    private fun showNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if(bottomNavigationBar.visibility == View.GONE){
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }

}