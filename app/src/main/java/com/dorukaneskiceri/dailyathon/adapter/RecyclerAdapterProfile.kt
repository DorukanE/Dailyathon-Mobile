package com.dorukaneskiceri.dailyathon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R


class RecyclerAdapterProfile(private val context: Context, private val arrayList: ArrayList<String>): RecyclerView.Adapter<RecyclerAdapterProfile.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_profile,parent,false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.textViewCategory.text = arrayList.get(position)
        holder.recyclerViewTags.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerViewTags.adapter = RecyclerAdapterTags()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ProfileViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewCategory: TextView = view.findViewById(R.id.textViewCategory)
        val recyclerViewTags: RecyclerView = view.findViewById(R.id.recyclerViewTags)
    }
}