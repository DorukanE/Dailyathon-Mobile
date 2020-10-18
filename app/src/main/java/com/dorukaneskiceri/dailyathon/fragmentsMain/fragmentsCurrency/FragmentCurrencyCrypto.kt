package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterCrypto
import com.dorukaneskiceri.dailyathon.model.CryptoModel
import kotlinx.android.synthetic.main.fragment_currency_crypto.*
import java.util.*
import kotlin.collections.ArrayList

class FragmentCurrencyCrypto : Fragment() {

    var adapter: RecyclerAdapterCrypto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_crypto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayListCrypto = ArrayList<CryptoModel>()
        val displayListCrypto = ArrayList<CryptoModel>()

        arrayListCrypto.add(CryptoModel("Bitcoin","11.760.56","1.75"))
        arrayListCrypto.add(CryptoModel("Etherium","11.760.56","1.75"))
        arrayListCrypto.add(CryptoModel("AVF","12.760.56","1.75"))
        arrayListCrypto.add(CryptoModel("AXC","11.760.56","1.75"))
        arrayListCrypto.add(CryptoModel("BCZ","11.760.56","1.75"))
        arrayListCrypto.add(CryptoModel("DRY","11.760.56","1.75"))
        displayListCrypto.addAll(arrayListCrypto)

        recyclerViewCrypto.layoutManager = LinearLayoutManager(view.context)
        adapter = RecyclerAdapterCrypto(displayListCrypto)
        recyclerViewCrypto.adapter = adapter

        searchViewFunction(arrayListCrypto,displayListCrypto)
    }

    private fun searchViewFunction(arrayListCrypto: ArrayList<CryptoModel>, displayListCrypto: ArrayList<CryptoModel>) {
        searchViewCrypto.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText!!.isNotEmpty()){
                    displayListCrypto.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    arrayListCrypto.forEach {
                        if(it.title.toLowerCase(Locale.getDefault()).contains(search) || it.description.toLowerCase(
                                Locale.getDefault()).contains(search)){
                            displayListCrypto.add(it)
                        }
                    }

                    recyclerViewCrypto.adapter!!.notifyDataSetChanged()

                }else{
                    displayListCrypto.clear()
                    displayListCrypto.addAll(arrayListCrypto)
                    recyclerViewCrypto.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })
    }
}