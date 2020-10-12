package com.dorukaneskiceri.dailyathon

import android.accounts.AuthenticatorDescription
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler__view_currency.view.*

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
        var textViewTitle: TextView
        var textViewDescription: TextView
        var textViewChangeRate: TextView

        init {
            textViewTitle = view.findViewById(R.id.textViewTitle)
            textViewDescription = view.findViewById(R.id.textViewDescription)
            textViewChangeRate = view.findViewById(R.id.textViewChangeRate)
        }
    }

}