package com.dorukaneskiceri.dailyathon.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewUserScoreBinding
import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel

class RecyclerAdapterUserScore(private val arrayListUserScore: ArrayList<UserLeagueListModel>): RecyclerView.Adapter<RecyclerAdapterUserScore.UserScoreHolder>() {

    private val colors: Array<String> = arrayOf("#5d778f","#626667")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserScoreHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewUserScoreBinding>(inflater, R.layout.recycler_view_user_score, parent, false)
        return UserScoreHolder(view)
    }

    override fun onBindViewHolder(holder: UserScoreHolder, position: Int) {
        holder.view.textViewUserCounterScore.text = arrayListUserScore.get(position).counterScore.toString()
        holder.view.textViewUserDraw.text = arrayListUserScore.get(position).draw.toString()
        holder.view.textViewUserLose.text = arrayListUserScore.get(position).lose.toString()
        holder.view.textViewUserPlace.text = arrayListUserScore.get(position).sequenceNo.toString()
        holder.view.textViewUserPlayed.text = arrayListUserScore.get(position).play.toString()
        holder.view.textViewUserWin.text = arrayListUserScore.get(position).win.toString()
        holder.view.textViewUserScore.text = arrayListUserScore.get(position).score.toString()
        holder.view.textViewUserPoint.text = arrayListUserScore.get(position).point.toString()
        holder.view.score = arrayListUserScore.get(position)
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 2]))
    }

    override fun getItemCount(): Int {
        return arrayListUserScore.size
    }

    class UserScoreHolder(var view: RecyclerViewUserScoreBinding): RecyclerView.ViewHolder(view.root){

    }
}