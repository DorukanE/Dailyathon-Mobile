package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.api_model.StockListModel
import kotlinx.android.synthetic.main.recycler_view_stock.view.*

class RecyclerAdapterStock(private val arrayList: ArrayList<StockListModel>): RecyclerView.Adapter<RecyclerAdapterStock.StockHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_stock,parent,false)
        return StockHolder(view)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.view.textViewTitleStock.text = arrayList.get(position).stockText
        holder.view.textViewDescriptionStock.text = arrayList.get(position).stockCode
        holder.view.textViewChangeRateStock.text = arrayList.get(position).stockLastPrice
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class StockHolder(var view: View): RecyclerView.ViewHolder(view){

    }
}