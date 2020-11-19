package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewCurrencyBinding
import com.dorukaneskiceri.dailyathon.model.CurrencyListModel

class RecyclerAdapterCurrency(private val arrayListCurrency: ArrayList<CurrencyListModel>): RecyclerView.Adapter<RecyclerAdapterCurrency.CurrencyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewCurrencyBinding>(inflater, R.layout.recycler__view_currency, parent, false)
        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.view.currency = arrayListCurrency.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListCurrency.size
    }

    class CurrencyHolder(var view: RecyclerViewCurrencyBinding): RecyclerView.ViewHolder(view.root){

    }

}