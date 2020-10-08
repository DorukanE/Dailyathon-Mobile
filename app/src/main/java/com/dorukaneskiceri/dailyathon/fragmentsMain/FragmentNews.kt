package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.NewsItemsPersonal
import com.dorukaneskiceri.dailyathon.NewsItemsTurkey
import com.dorukaneskiceri.dailyathon.NewsItemsWorld
import com.dorukaneskiceri.dailyathon.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.recycler_view_news.*
import kotlinx.android.synthetic.main.recycler_view_news.view.*

class FragmentNews : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getWorldNewsView()
        getPersonalNewsView()
        getTurkeyNewsView()
    }

    private fun getTurkeyNewsView() {
        recyclerViewNewsTurkey.layoutManager = LinearLayoutManager(view?.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewNewsTurkey.adapter = adapter

        for (i in 1..3){
            adapter.add(NewsItemsTurkey())
        }

        adapter.setOnItemClickListener { item, view ->

        }
    }

    private fun getPersonalNewsView() {
        recyclerViewNewsPersonal.layoutManager = LinearLayoutManager(view?.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewNewsPersonal.adapter = adapter

        for(i in 1..3){
            adapter.add(NewsItemsPersonal())
        }

        adapter.setOnItemClickListener { item, view ->

        }
    }

    private fun getWorldNewsView(){
        recyclerViewNews.layoutManager = LinearLayoutManager(view?.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewNews.adapter = adapter

        for(i in 1..3){
            adapter.add(NewsItemsWorld())
        }

        adapter.setOnItemClickListener { item, view ->

        }
    }
}