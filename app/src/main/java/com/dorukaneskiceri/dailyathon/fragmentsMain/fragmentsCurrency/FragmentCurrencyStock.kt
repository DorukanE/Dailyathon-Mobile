package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterStock
import com.dorukaneskiceri.dailyathon.model.StockModel
import kotlinx.android.synthetic.main.fragment_currency_stock.*
import java.util.*
import kotlin.collections.ArrayList

class FragmentCurrencyStock : Fragment() {

    var adapter: RecyclerAdapterStock? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_stock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayListStock = ArrayList<StockModel>()
        val displayListStock = ArrayList<StockModel>()

        arrayListStock.add(StockModel("Arçelik","11.300","3"))
        arrayListStock.add(StockModel("Beko","11.300","3"))
        arrayListStock.add(StockModel("Vakıfbank","11.300","3"))
        arrayListStock.add(StockModel("Ziraat Bankası","11.300","3"))
        arrayListStock.add(StockModel("Ülker","11.300","3"))
        arrayListStock.add(StockModel("Eti","11.300","3"))
        arrayListStock.add(StockModel("Vestel","11.300","3"))
        displayListStock.addAll(arrayListStock)

        recyclerViewStock.layoutManager = LinearLayoutManager(view.context)
        adapter = RecyclerAdapterStock(displayListStock)
        recyclerViewStock.adapter = adapter

        searchViewFunction(arrayListStock,displayListStock)
    }

    private fun searchViewFunction(arrayList: ArrayList<StockModel>, displayList: ArrayList<StockModel>) {
        searchViewStock.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    displayList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    arrayList.forEach {
                        if(it.title.toLowerCase(Locale.getDefault()).contains(search)){
                            displayList.add(it)
                        }
                    }

                    recyclerViewStock.adapter!!.notifyDataSetChanged()

                }else{
                    displayList.clear()
                    displayList.addAll(arrayList)
                    recyclerViewStock.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })
    }
}