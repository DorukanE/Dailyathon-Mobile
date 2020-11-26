package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewUserLeaguesBinding
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsSport.FragmentUserSportDirections
import com.dorukaneskiceri.dailyathon.model.UserLeagueTableNameModel
import kotlinx.android.synthetic.main.recycler_view_user_leagues.view.*

class RecyclerAdapterUserLeagues(private val arrayListUserLeagues: ArrayList<UserLeagueTableNameModel>) :
    RecyclerView.Adapter<RecyclerAdapterUserLeagues.UserLeagueHolder>(), UserLeagueClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserLeagueHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewUserLeaguesBinding>(
            inflater,
            R.layout.recycler_view_user_leagues,
            parent,
            false
        )
        return UserLeagueHolder(view)
    }

    override fun onBindViewHolder(holder: UserLeagueHolder, position: Int) {
        holder.view.listener = this
        holder.view.userLeague = arrayListUserLeagues.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListUserLeagues.size
    }

    class UserLeagueHolder(var view: RecyclerViewUserLeaguesBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onLeagueClicked(it: View) {
        val leagueTableName = it.textViewUserLeagueName.text.toString()
        val sportName = it.textViewUserSportName.text.toString()
        val action = FragmentUserSportDirections.actionFragmentUserSportToFragmentUserScore(leagueTableName, sportName)
        Navigation.findNavController(it).navigate(action)
    }
}