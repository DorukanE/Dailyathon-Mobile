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
import kotlinx.android.synthetic.main.recycler_view_daily_news.view.*

class RecyclerAdapterDailyNews(
    private val arrayListDailyNews: ArrayList<NewsListModel>,
    private val isHere: Boolean,
) :
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
        val newsTitle = it.textViewDNewsTitle.text.toString()
        val newsDescription = it.textViewDNewsDescription.text.toString()
        val newsType = it.textViewDNewsType.text.toString()

        if (isHere) {
            val action = FragmentNewsDirections.actionDestinationNewsToFragmentDailyNewsDetail(
                newsTitle,
                newsDescription,
                newsType
            )
            Navigation.findNavController(it).navigate(action)
        } else {
            val action =
                FragmentDailyNewsDirections.actionFragmentDailyNewsToFragmentDailyNewsDetail(
                    newsTitle,
                    newsDescription,
                    newsType
                )
            Navigation.findNavController(it).navigate(action)
        }

    }

}