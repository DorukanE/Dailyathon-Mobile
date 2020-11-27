package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.content.Context.MODE_PRIVATE
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
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterCrypto
import com.dorukaneskiceri.dailyathon.model.CryptoListModel
import com.dorukaneskiceri.dailyathon.view_model.CryptoListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_currency_crypto.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.collections.ArrayList

class FragmentCurrencyCrypto : Fragment() {

    private lateinit var viewModelCryptoList: CryptoListViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel
    var adapter: RecyclerAdapterCrypto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_crypto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelCryptoList = ViewModelProvider(this).get(CryptoListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val arrayListCrypto = ArrayList<CryptoListModel>()
        val displayListCrypto = ArrayList<CryptoListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getToken(userEmail!!, userPassword!!, sharedPreferencesToken)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getCryptoList(arrayListCrypto, token!!, displayListCrypto)
        }

        searchViewCrypto.setOnQueryTextFocusChangeListener { v, b ->
            searchViewFunction(arrayListCrypto, displayListCrypto)
        }
    }

    private fun getCryptoList(
        arrayListCrypto: java.util.ArrayList<CryptoListModel>,
        token: String,
        displayListCrypto: ArrayList<CryptoListModel>
    ) {
        recyclerViewCrypto.layoutManager = LinearLayoutManager(view?.context)
        viewModelCryptoList.getCryptoList(token, requireView())
        viewModelCryptoList.cryptoList.observe(viewLifecycleOwner, { response ->
            arrayListCrypto.add(response)
            displayListCrypto.add(response)
            adapter = RecyclerAdapterCrypto(displayListCrypto)
            recyclerViewCrypto.adapter = adapter
            progressBar12.visibility = View.INVISIBLE
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
        arrayListCrypto: ArrayList<CryptoListModel>,
        displayListCrypto: ArrayList<CryptoListModel>
    ) {
        searchViewCrypto.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    displayListCrypto.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    arrayListCrypto.forEach {
                        if (it.cryptoName.toLowerCase(Locale.getDefault()).contains(search) || it.cryptoCode.toLowerCase(
                                Locale.getDefault()).contains(search)
                        ) {
                            displayListCrypto.add(it)
                        }
                    }
                    recyclerViewCrypto.adapter!!.notifyDataSetChanged()
                } else {
                    displayListCrypto.clear()
                    displayListCrypto.addAll(arrayListCrypto)
                    recyclerViewCrypto.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })
    }
}