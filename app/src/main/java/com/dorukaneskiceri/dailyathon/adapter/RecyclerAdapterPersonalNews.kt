package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewNewsBinding
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentNewsDirections
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsNews.FragmentPersonalNewsDirections
import com.dorukaneskiceri.dailyathon.model.api_model.NewsListModel
import kotlinx.android.synthetic.main.recycler_view_news.view.*

class RecyclerAdapterPersonalNews(
    private val arrayListNews: ArrayList<NewsListModel>,
    private val isHere: Boolean,
    private val newsDate: String
) :
    RecyclerView.Adapter<RecyclerAdapterPersonalNews.NewsPersonalHolder>(),
    PersonalNewsClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPersonalHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewNewsBinding>(
            inflater,
            R.layout.recycler_view_news,
            parent,
            false
        )
        return NewsPersonalHolder(view)
    }

    override fun onBindViewHolder(holder: NewsPersonalHolder, position: Int) {
        holder.view.textViewPNewsDate.text = "Tarih: ${newsDate}"
        holder.view.news = arrayListNews.get(position)
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return arrayListNews.size
    }

    class NewsPersonalHolder(var view: RecyclerViewNewsBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onPersonalNewsClicked(it: View) {
        val newsTitle = it.textViewPNewsTitle.text.toString()
        val newsContent = it.textViewPNewsContent.text.toString()
        val newsType = it.textViewPNewsType.text.toString()
        val newsDate = it.textViewPNewsDate.text.toString()

        if (isHere) {
            val action = FragmentNewsDirections.actionDestinationNewsToFragmentPersonalNewsDetail(
                newsTitle,
                newsContent,
                newsType,
                newsDate
            )
            Navigation.findNavController(it).navigate(action)
        } else {
            val action =
                FragmentPersonalNewsDirections.actionFragmentPersonalNewsToFragmentPersonalNewsDetail(
                    newsTitle,
                    newsContent,
                    newsType,
                    newsDate
                )
            Navigation.findNavController(it).navigate(action)
        }

    }
}