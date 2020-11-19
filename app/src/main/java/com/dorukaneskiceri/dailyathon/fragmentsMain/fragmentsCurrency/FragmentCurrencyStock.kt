package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterStock
import com.dorukaneskiceri.dailyathon.model.StockListModel
import com.dorukaneskiceri.dailyathon.view_model.StockListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_currency_stock.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.collections.ArrayList

class FragmentCurrencyStock : Fragment() {

    private lateinit var viewModelStockList: StockListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    var adapter: RecyclerAdapterStock? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_stock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelStockList = ViewModelProvider(this).get(StockListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", Context.MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", Context.MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", Context.MODE_PRIVATE)

        val arrayListStock = ArrayList<StockListModel>()
        val displayListStock = ArrayList<StockListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getToken(userEmail!!, userPassword!!, sharedPreferencesToken)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getStockList(arrayListStock, token!!, displayListStock)
        }

        searchViewStock.setOnQueryTextFocusChangeListener { v, b ->
            searchViewFunction(arrayListStock, displayListStock)
        }
    }

    private fun getStockList(
        arrayListStock: java.util.ArrayList<StockListModel>,
        token: String,
        displayListStock: java.util.ArrayList<StockListModel>
    ) {
        recyclerViewStock.layoutManager = LinearLayoutManager(view?.context)
        viewModelStockList.getStockList(token)
        viewModelStockList.stockList.observe(viewLifecycleOwner, { response ->
            arrayListStock.add(response)
            displayListStock.add(response)
            adapter = RecyclerAdapterStock(displayListStock)
            recyclerViewStock.adapter = adapter
            progressBar13.visibility = View.INVISIBLE
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

    private fun searchViewFunction(
        arrayList: ArrayList<StockListModel>,
        displayListStock: ArrayList<StockListModel>
    ) {
        searchViewStock.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    displayListStock.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    arrayList.forEach {
                        if (it.stockText.toLowerCase(Locale.getDefault())
                                .contains(search) || it.stockCode.toLowerCase(
                                Locale.getDefault()
                            ).contains(search)
                        ) {
                            displayListStock.add(it)
                        }
                    }
                    recyclerViewStock.adapter!!.notifyDataSetChanged()
                } else {
                    displayListStock.clear()
                    displayListStock.addAll(arrayList)
                    recyclerViewStock.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })
    }
}