package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.items.CurrencyItems
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_currency_pager.*

class FragmentCurrencyPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCurrency.layoutManager = LinearLayoutManager(view.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewCurrency.adapter = adapter

        adapter.add(CurrencyItems())
        adapter.add(CurrencyItems())
        adapter.add(CurrencyItems())
        adapter.add(CurrencyItems())
        adapter.add(CurrencyItems())
        adapter.add(CurrencyItems())
        adapter.add(CurrencyItems())
        adapter.add(CurrencyItems())
        adapter.add(CurrencyItems())
    }
}