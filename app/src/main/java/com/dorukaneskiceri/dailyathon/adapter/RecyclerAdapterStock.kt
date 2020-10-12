package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.StockModel

class RecyclerAdapterStock(private val arrayList: ArrayList<StockModel>): RecyclerView.Adapter<RecyclerAdapterStock.StockHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_stock,parent,false)
        return StockHolder(view)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.textViewTitle.text = arrayList.get(position).title
        holder.textViewDescription.text = arrayList.get(position).description
        holder.textViewChangeRate.text = arrayList.get(position).changeRate
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class StockHolder(val view: View): RecyclerView.ViewHolder(view){
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitleStock)
        val textViewDescription: TextView = view.findViewById(R.id.textViewDescriptionStock)
        val textViewChangeRate: TextView = view.findViewById(R.id.textViewChangeRateStock)
    }
}