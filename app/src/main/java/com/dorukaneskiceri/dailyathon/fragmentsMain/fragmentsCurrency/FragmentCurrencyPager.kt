package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterCurrency
import com.dorukaneskiceri.dailyathon.databinding.FragmentCurrencyPagerBinding
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewCurrencyBinding
import com.dorukaneskiceri.dailyathon.model.api_model.CurrencyListModel
import com.dorukaneskiceri.dailyathon.view_model.CurrencyListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_currency_pager.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FragmentCurrencyPager : Fragment() {

    private lateinit var viewModelCurrencyList: CurrencyListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var dataBinding: FragmentCurrencyPagerBinding
    var adapter: RecyclerAdapterCurrency? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency_pager, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelCurrencyList = ViewModelProvider(this).get(CurrencyListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", Context.MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", Context.MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", Context.MODE_PRIVATE)

        val arrayListCurrency = ArrayList<CurrencyListModel>()
        val displayListCurrency = ArrayList<CurrencyListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getToken(userEmail!!, userPassword!!, sharedPreferencesToken)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getCurrencyList(arrayListCurrency, token!!, displayListCurrency)
        }

        searchViewCurrency.setOnQueryTextFocusChangeListener { v, b ->
            searchViewFunction(arrayListCurrency, displayListCurrency)
        }
    }

    private fun getCurrencyList(
        arrayListCurrency: java.util.ArrayList<CurrencyListModel>,
        token: String,
        displayListCurrency: java.util.ArrayList<CurrencyListModel>
    ) {
        recyclerViewCurrency.layoutManager = LinearLayoutManager(view?.context)
        viewModelCurrencyList.getCurrencyList(token)
        viewModelCurrencyList.currencyList.observe(viewLifecycleOwner, { response ->
            val updateDate = getCurrencyDate(response)
            dataBinding.textViewUpdateDate.text = "Son güncelleme tarihi: ${updateDate}"
            arrayListCurrency.add(response)
            displayListCurrency.add(response)
            adapter = RecyclerAdapterCurrency(displayListCurrency)
            recyclerViewCurrency.adapter = adapter
            progressBar14.visibility = View.INVISIBLE
        })
    }

    private fun getCurrencyDate(response: CurrencyListModel): String {
        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.currencyDatetime)
        return outputFormat.format(date)
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

    private fun searchViewFunction(
        arrayListCurrency: ArrayList<CurrencyListModel>,
        displayListCurrency: ArrayList<CurrencyListModel>
    ) {
        searchViewCurrency.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    displayListCurrency.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    arrayListCurrency.forEach {
                        if (it.currencyName.toLowerCase(Locale.getDefault())
                                .contains(search) || it.currencyCode.toLowerCase(
                                Locale.getDefault()
                            ).contains(search)
                        ) {
                            displayListCurrency.add(it)
                        }
                    }
                    recyclerViewCurrency.adapter!!.notifyDataSetChanged()
                } else {
                    displayListCurrency.clear()
                    displayListCurrency.addAll(arrayListCurrency)
                    recyclerViewCurrency.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })
    }
}
