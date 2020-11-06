package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewChosenBinding
import com.dorukaneskiceri.dailyathon.model.api_model.UserEntertainmentModel

class RecyclerAdapterUserEntertainment(private val arrayListEntertainment: ArrayList<UserEntertainmentModel>): RecyclerView.Adapter<RecyclerAdapterUserEntertainment.UserEntertainmentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEntertainmentHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewChosenBinding>(inflater, R.layout.recycler_view_chosen, parent, false)
        return UserEntertainmentHolder(view)
    }

    override fun onBindViewHolder(holder: UserEntertainmentHolder, position: Int) {
        holder.view.entertainment = arrayListEntertainment.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListEntertainment.size
    }

    class UserEntertainmentHolder(var view: RecyclerViewChosenBinding): RecyclerView.ViewHolder(view.root){

    }
}