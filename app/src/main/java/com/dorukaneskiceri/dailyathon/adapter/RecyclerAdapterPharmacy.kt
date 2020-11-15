package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.api_model.PharmacyListModel
import kotlinx.android.synthetic.main.recycler_view_pharmacy.view.*

class RecyclerAdapterPharmacy(private val arrayList: ArrayList<PharmacyListModel>): RecyclerView.Adapter<RecyclerAdapterPharmacy.PharmacyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_pharmacy, parent, false)
        return PharmacyHolder(view)
    }

    override fun onBindViewHolder(holder: PharmacyHolder, position: Int) {
        holder.view.textViewPTitle.text = arrayList.get(position).pharmacyName
        holder.view.textViewPAddress.text = arrayList.get(position).pharmacyAddress
        holder.view.textViewPDistrict.text = arrayList.get(position).pharmacyDist
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class PharmacyHolder(var view: View): RecyclerView.ViewHolder(view){

    }
}