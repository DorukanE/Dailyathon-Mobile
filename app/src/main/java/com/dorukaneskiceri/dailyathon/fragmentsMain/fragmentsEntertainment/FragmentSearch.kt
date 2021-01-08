package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsEntertainment

import android.app.DatePickerDialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterSearchEntertainment
import com.dorukaneskiceri.dailyathon.model.EntertainmentListModel
import com.dorukaneskiceri.dailyathon.view_model.EntertainmentListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FragmentSearch : Fragment() {

    private lateinit var viewModelEntertainmentList: EntertainmentListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var adapter: RecyclerAdapterSearchEntertainment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelEntertainmentList =
            ViewModelProvider(this).get(EntertainmentListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListSearchEntertainment = ArrayList<EntertainmentListModel>()
        val displayListSearchEntertainment = ArrayList<EntertainmentListModel>()
        recyclerViewSearch.layoutManager = LinearLayoutManager(view.context)

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")
        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getEntertainments(token!!, arrayListSearchEntertainment, displayListSearchEntertainment)
        }

        searchViewEntertainment.setOnQueryTextFocusChangeListener { view, b ->
            searchViewFunction(
                arrayListSearchEntertainment,
                displayListSearchEntertainment
            )
        }
    }

    private fun searchViewFunction(
        arrayListSearchEntertainment: java.util.ArrayList<EntertainmentListModel>,
        displayListSearchEntertainment: java.util.ArrayList<EntertainmentListModel>
    ) {
        searchViewEntertainment.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    displayListSearchEntertainment.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    arrayListSearchEntertainment.forEach {
                        if (it.entertainmentName.toLowerCase(Locale.getDefault())
                                .contains(search) || it.entertainmentCity.toLowerCase(
                                Locale.getDefault()
                            ).contains(search)
                        ) {
                            displayListSearchEntertainment.add(it)
                        }
                    }
                    recyclerViewSearch.adapter!!.notifyDataSetChanged()

                } else {
                    displayListSearchEntertainment.clear()
                    displayListSearchEntertainment.addAll(arrayListSearchEntertainment)
                    recyclerViewSearch.adapter!!.notifyDataSetChanged()
                }

                return true
            }
        })
    }

    private fun getEntertainments(
        token: String,
        arrayListSearchEntertainment: ArrayList<EntertainmentListModel>,
        displayListSearchEntertainment: ArrayList<EntertainmentListModel>
    ) {
        viewModelEntertainmentList.getEntertainmentList(requireView(), token)
        viewModelEntertainmentList.entertainmentList.observe(viewLifecycleOwner, { response ->
            val startDate = getStartDate(response)
            val dueDate = getDueDate(response)
            arrayListSearchEntertainment.add(response)
            displayListSearchEntertainment.add(response)
            adapter = RecyclerAdapterSearchEntertainment(
                displayListSearchEntertainment,
                startDate,
                dueDate
            )
            recyclerViewSearch.adapter = adapter
            progressBar20.visibility = View.INVISIBLE
        })
    }

    private fun getDueDate(response: EntertainmentListModel): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.entertainmentStartDate)
        return outputFormat.format(date)
    }

    private fun getStartDate(response: EntertainmentListModel): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.entertainmentDueDate)
        return outputFormat.format(date)
    }

    private fun getUser(
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