package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.CryptoModel
import com.dorukaneskiceri.dailyathon.model.api_model.CryptoListModel
import kotlinx.android.synthetic.main.recycler_view_crypto.view.*

class RecyclerAdapterCrypto(private val arrayList: ArrayList<CryptoListModel>): RecyclerView.Adapter<RecyclerAdapterCrypto.CryptoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_crypto,parent,false)
        return CryptoHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.view.textViewTitleCrypto.text = arrayList.get(position).cryptoCurrency
        holder.view.textViewDescriptionCrypto.text = arrayList.get(position).cryptoName
        holder.view.textViewChangeRateCrypto.text = arrayList.get(position).cryptochangeDay
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class CryptoHolder(var view: View): RecyclerView.ViewHolder(view){

    }

}