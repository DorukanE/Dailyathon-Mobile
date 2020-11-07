package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewDailyNewsBinding
import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel

class RecyclerAdapterDailyNews(private val arrayListDailyNews: ArrayList<NewsListModel>): RecyclerView.Adapter<RecyclerAdapterDailyNews.DailyNewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyNewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewDailyNewsBinding>(inflater, R.layout.recycler_view_daily_news, parent, false)
        return DailyNewsHolder(view)
    }

    override fun onBindViewHolder(holder: DailyNewsHolder, position: Int) {
        holder.view.dailyNews = arrayListDailyNews.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListDailyNews.size
    }

    class DailyNewsHolder(var view: RecyclerViewDailyNewsBinding): RecyclerView.ViewHolder(view.root){

    }

}