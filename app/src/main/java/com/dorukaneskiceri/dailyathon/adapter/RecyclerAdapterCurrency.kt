package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.CurrencyModel

class RecyclerAdapterCurrency(private val currencyModel: ArrayList<CurrencyModel>): RecyclerView.Adapter<RecyclerAdapterCurrency.CurrencyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler__view_currency,parent,false)
        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.textViewTitle.text = currencyModel.get(position).title
        holder.textViewDescription.text = currencyModel.get(position).description
        holder.textViewChangeRate.text = currencyModel.get(position).changeRate
    }

    override fun getItemCount(): Int {
        return currencyModel.size
    }

    class CurrencyHolder(val view: View): RecyclerView.ViewHolder(view){
        var textViewTitle: TextView = view.findViewById(R.id.textViewTitleCurrency)
        var textViewDescription: TextView = view.findViewById(R.id.textViewDescriptionCurrency)
        var textViewChangeRate: TextView = view.findViewById(R.id.textViewChangeRateCurrency)

    }

}