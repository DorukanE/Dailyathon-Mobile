package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.CurrencyModel
import com.dorukaneskiceri.dailyathon.model.api_model.CurrencyListModel
import kotlinx.android.synthetic.main.recycler__view_currency.view.*

class RecyclerAdapterCurrency(private val currencyModel: ArrayList<CurrencyListModel>): RecyclerView.Adapter<RecyclerAdapterCurrency.CurrencyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler__view_currency,parent,false)
        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.view.textViewTitleCurrency.text = currencyModel.get(position).currencyCode
        holder.view.textViewDescriptionCurrency.text = currencyModel.get(position).currencyName
        holder.view.textViewChangeRateCurrency.text = currencyModel.get(position).currencyBuyValue
    }

    override fun getItemCount(): Int {
        return currencyModel.size
    }

    class CurrencyHolder(var view: View): RecyclerView.ViewHolder(view){

    }

}