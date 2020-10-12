package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterCrypto
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterCurrency
import com.dorukaneskiceri.dailyathon.items.CryptoItems
import com.dorukaneskiceri.dailyathon.model.CryptoModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_currency_crypto.*

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
        arrayListCrypto.add(CryptoModel("AVF","11.760.56","1.75"))
        arrayListCrypto.add(CryptoModel("AXC","11.760.56","1.75"))
        arrayListCrypto.add(CryptoModel("BCZ","11.760.56","1.75"))
        arrayListCrypto.add(CryptoModel("DRY","11.760.56","1.75"))
        displayListCrypto.addAll(arrayListCrypto)

        recyclerViewCrypto.layoutManager = LinearLayoutManager(view.context)
        adapter = RecyclerAdapterCrypto(displayListCrypto)
        recyclerViewCrypto.adapter = adapter


    }
}