package com.dorukaneskiceri.dailyathon.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.activity.MapsActivityPharmacy
import com.dorukaneskiceri.dailyathon.model.PharmacyListModel
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        holder.view.setOnClickListener {v ->
            val dialog = BottomSheetDialog(v.context)
            val view = LayoutInflater.from(v.context).inflate(R.layout.dialog_layout, null)
            dialog.setContentView(view)
            dialog.show()

            val textViewCancel: TextView = view.findViewById(R.id.textViewCancel)
            val textViewMakeCall: TextView = view.findViewById(R.id.textViewMakeCall)
            val textViewFind: TextView = view.findViewById(R.id.textViewFind)

            textViewCancel.setOnClickListener {
                dialog.dismiss()
            }
            textViewFind.setOnClickListener {
                val intent = Intent(it.context, MapsActivityPharmacy::class.java)
                intent.putExtra("pharmacyName", arrayList.get(position).pharmacyName)
                intent.putExtra("pharmacyLocation", arrayList.get(position).pharmacyLocation)
                startActivity(it.context, intent, null)
            }
            textViewMakeCall.setOnClickListener {
                val number = arrayList.get(position).pharmacyPhone
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
                startActivity(it.context, intent, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class PharmacyHolder(var view: View): RecyclerView.ViewHolder(view){

    }
}