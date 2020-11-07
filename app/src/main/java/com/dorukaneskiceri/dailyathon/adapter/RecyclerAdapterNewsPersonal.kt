package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewNewsBinding
import com.dorukaneskiceri.dailyathon.model.api_model.UserNewsListModel

class RecyclerAdapterNewsPersonal(private val arrayListNews: ArrayList<UserNewsListModel>): RecyclerView.Adapter<RecyclerAdapterNewsPersonal.NewsPersonalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPersonalHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewNewsBinding>(inflater, R.layout.recycler_view_news, parent, false)
        return NewsPersonalHolder(view)
    }

    override fun onBindViewHolder(holder: NewsPersonalHolder, position: Int) {
        holder.view.news = arrayListNews.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListNews.size
    }

    class NewsPersonalHolder(var view: RecyclerViewNewsBinding): RecyclerView.ViewHolder(view.root){

    }
}