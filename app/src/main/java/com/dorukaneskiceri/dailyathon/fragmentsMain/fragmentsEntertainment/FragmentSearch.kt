package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsEntertainment

import android.app.DatePickerDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterCityEntertainment
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterSearchEntertainment
import com.dorukaneskiceri.dailyathon.model.EntertainmentListModel
import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel
import com.dorukaneskiceri.dailyathon.view_model.EntertainmentListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_chosen_city.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*

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

        viewModelEntertainmentList = ViewModelProvider(this).get(EntertainmentListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListSearchEntertainment= ArrayList<EntertainmentListModel>()
        recyclerViewSearch.layoutManager = LinearLayoutManager(view.context)

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")
        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getEntertainments(token!!, arrayListSearchEntertainment)
        }

        textViewStartDate.setOnClickListener {
            showDateDialogStart(it)
        }

        textViewEndDate.setOnClickListener {
            showDateDialogEnd(it)
        }
    }

    private fun getEntertainments(
        token: String,
        arrayListSearchEntertainment: ArrayList<EntertainmentListModel>
    ) {
        viewModelEntertainmentList.getEntertainmentList(requireView(), token)
        viewModelEntertainmentList.entertainmentList.observe(viewLifecycleOwner, { response ->
//            val startDate = getUserCityStartDate(response)
//            val dueDate = getUSerCityDueDate(response)
            arrayListSearchEntertainment.add(response)
            adapter = RecyclerAdapterSearchEntertainment(arrayListSearchEntertainment)
            recyclerViewSearch.adapter = adapter
            progressBar20.visibility = View.INVISIBLE
        })
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, {response ->
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
        })
    }

    private fun showDateDialogStart(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            val savedString = "$i3 / ${i2 + 1} / $i"
            textViewStartDate.setTextColor(ContextCompat.getColor(view.context,R.color.colorWhite))
            textViewStartDate.text = "Başlangıç: $savedString"
        },year,month,day).show()
    }

    private fun showDateDialogEnd(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            val savedString = "$i3 / ${i2 + 1} / $i"
            textViewEndDate.setTextColor(ContextCompat.getColor(view.context,R.color.colorWhite))
            textViewEndDate.text = "Bitiş: $savedString"
        },year,month,day).show()
    }
}