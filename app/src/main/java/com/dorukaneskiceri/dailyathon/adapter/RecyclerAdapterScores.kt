package com.dorukaneskiceri.dailyathon.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewScoreListBinding
import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel

class RecyclerAdapterScores(private val arrayListScore: ArrayList<UserLeagueListModel>): RecyclerView.Adapter<RecyclerAdapterScores.ScoreListHolder>() {

    private val colors: Array<String> = arrayOf("#5d778f","#626667")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewScoreListBinding>(inflater, R.layout.recycler_view_score_list, parent, false)
        return ScoreListHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreListHolder, position: Int) {
        holder.view.textViewCounterScore.text = arrayListScore.get(position).counterScore.toString()
        holder.view.textViewDraw.text = arrayListScore.get(position).draw.toString()
        holder.view.textViewLose.text = arrayListScore.get(position).lose.toString()
        holder.view.textViewPlace.text = arrayListScore.get(position).sequenceNo.toString()
        holder.view.textViewPlayed.text = arrayListScore.get(position).play.toString()
        holder.view.textViewWin.text = arrayListScore.get(position).win.toString()
        holder.view.textViewScore.text = arrayListScore.get(position).score.toString()
        holder.view.textViewPoint.text = arrayListScore.get(position).point.toString()
        holder.view.score = arrayListScore.get(position)
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 2]))
    }

    override fun getItemCount(): Int {
        return arrayListScore.size
    }

    class ScoreListHolder(var view: RecyclerViewScoreListBinding): RecyclerView.ViewHolder(view.root){

    }
}