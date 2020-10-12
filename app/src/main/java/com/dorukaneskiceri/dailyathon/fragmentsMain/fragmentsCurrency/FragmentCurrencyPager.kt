package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.graphics.ColorSpace
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.HeaderViewListAdapter
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.CurrencyModel
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.RecyclerAdapterCurrency
import com.dorukaneskiceri.dailyathon.items.CurrencyItems
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_currency_pager.*
import kotlinx.coroutines.flow.channelFlow
import java.util.*

class FragmentCurrencyPager : Fragment() {

    var adapter: RecyclerAdapterCurrency? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayListCurrency = ArrayList<CurrencyModel>()
        val displayListCurrency = ArrayList<CurrencyModel>()

        recyclerViewCurrency.layoutManager = LinearLayoutManager(view.context)

        arrayListCurrency.add(CurrencyModel("Euro","10","5"))
        arrayListCurrency.add(CurrencyModel("Dolar","8","1"))
        arrayListCurrency.add(CurrencyModel("Kanada Doları","10","5"))
        arrayListCurrency.add(CurrencyModel("TL","10","5"))
        arrayListCurrency.add(CurrencyModel("Sterlin","10","5"))
        displayListCurrency.addAll(arrayListCurrency)

        adapter = RecyclerAdapterCurrency(displayListCurrency)
        recyclerViewCurrency.adapter = adapter
        adapter!!.notifyDataSetChanged()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText!!.isNotEmpty()){
                    displayListCurrency.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    arrayListCurrency.forEach {
                        if(it.title.toLowerCase(Locale.getDefault()).contains(search)){
                            displayListCurrency.add(it)
                        }
                    }

                    recyclerViewCurrency.adapter!!.notifyDataSetChanged()

                }else{
                    displayListCurrency.clear()
                    displayListCurrency.addAll(arrayListCurrency)
                    recyclerViewCurrency.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })
    }
}
