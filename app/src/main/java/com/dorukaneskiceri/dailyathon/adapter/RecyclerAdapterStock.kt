package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewStockBinding
import com.dorukaneskiceri.dailyathon.model.StockListModel

class RecyclerAdapterStock(private val arrayList: ArrayList<StockListModel>): RecyclerView.Adapter<RecyclerAdapterStock.StockHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewStockBinding>(inflater, R.layout.recycler_view_stock, parent, false)
        return StockHolder(view)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.view.stock = arrayList.get(position)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class StockHolder(var view: RecyclerViewStockBinding): RecyclerView.ViewHolder(view.root){

    }
}