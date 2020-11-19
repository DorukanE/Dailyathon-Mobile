package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewLeagueListBinding
import com.dorukaneskiceri.dailyathon.model.LeagueListModel

class RecyclerAdapterLeagues(private val arrayListLeague: ArrayList<LeagueListModel>): RecyclerView.Adapter<RecyclerAdapterLeagues.LeagueHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewLeagueListBinding>(inflater, R.layout.recycler_view_league_list, parent, false)
        return LeagueHolder(view)
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.view.league = arrayListLeague.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListLeague.size
    }

    class LeagueHolder(var view: RecyclerViewLeagueListBinding): RecyclerView.ViewHolder(view.root){

    }
}