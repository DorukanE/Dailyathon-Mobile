package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewSearchEntertainmentBinding
import com.dorukaneskiceri.dailyathon.model.EntertainmentListModel

class RecyclerAdapterSearchEntertainment(private val arrayListSearch: ArrayList<EntertainmentListModel>) :
    RecyclerView.Adapter<RecyclerAdapterSearchEntertainment.EntertainmentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntertainmentHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewSearchEntertainmentBinding>(
            inflater,
            R.layout.recycler_view_search_entertainment,
            parent,
            false
        )
        return EntertainmentHolder(view)
    }

    override fun onBindViewHolder(holder: EntertainmentHolder, position: Int) {
        holder.view.searchEntertainment = arrayListSearch.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListSearch.size
    }

    class EntertainmentHolder(val view: RecyclerViewSearchEntertainmentBinding) :
        RecyclerView.ViewHolder(view.root) {

    }
}