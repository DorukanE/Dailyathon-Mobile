package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewDailyNewsBinding
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentNewsDirections
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsNews.FragmentDailyNewsDirections
import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel

class RecyclerAdapterDailyNews(private val arrayListDailyNews: ArrayList<NewsListModel>, private val isHere: Boolean) :
    RecyclerView.Adapter<RecyclerAdapterDailyNews.DailyNewsHolder>(), DailyNewsClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyNewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewDailyNewsBinding>(
            inflater,
            R.layout.recycler_view_daily_news,
            parent,
            false
        )
        return DailyNewsHolder(view)
    }

    override fun onBindViewHolder(holder: DailyNewsHolder, position: Int) {
        holder.view.dailyNews = arrayListDailyNews.get(position)
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return arrayListDailyNews.size
    }

    class DailyNewsHolder(var view: RecyclerViewDailyNewsBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onDailyNewsClicked(it: View) {
        if(isHere){
            val action = FragmentNewsDirections.actionDestinationNewsToFragmentDailyNewsDetail()
            Navigation.findNavController(it).navigate(action)
        }else{
            val action = FragmentDailyNewsDirections.actionFragmentDailyNewsToFragmentDailyNewsDetail()
            Navigation.findNavController(it).navigate(action)
        }

    }

}