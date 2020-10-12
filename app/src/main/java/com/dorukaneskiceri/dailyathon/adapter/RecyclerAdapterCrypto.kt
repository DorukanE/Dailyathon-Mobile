package com.dorukaneskiceri.dailyathon.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.CryptoModel

class RecyclerAdapterCrypto(private val arrayList: ArrayList<CryptoModel>): RecyclerView.Adapter<RecyclerAdapterCrypto.CryptoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_crypto,parent,false)
        return CryptoHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.textViewTitle.text = arrayList.get(position).title
        holder.textViewDescription.text = arrayList.get(position).description
        holder.textViewChangeRate.text = arrayList.get(position).changeRate
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class CryptoHolder(val view: View): RecyclerView.ViewHolder(view){
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitleCrypto)
        val textViewDescription: TextView = view.findViewById(R.id.textViewDescriptionCrypto)
        val textViewChangeRate: TextView = view.findViewById(R.id.textViewChangeRateCrypto)
    }

}