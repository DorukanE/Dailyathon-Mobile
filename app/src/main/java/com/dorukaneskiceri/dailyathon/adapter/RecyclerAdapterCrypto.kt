package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewCryptoBinding
import com.dorukaneskiceri.dailyathon.model.api_model.CryptoListModel

class RecyclerAdapterCrypto(private val arrayListCrypto: ArrayList<CryptoListModel>): RecyclerView.Adapter<RecyclerAdapterCrypto.CryptoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewCryptoBinding>(inflater, R.layout.recycler_view_crypto, parent, false)
        return CryptoHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.view.crypto = arrayListCrypto.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListCrypto.size
    }

    class CryptoHolder(var view: RecyclerViewCryptoBinding): RecyclerView.ViewHolder(view.root){

    }

}