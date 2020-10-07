package com.dorukaneskiceri.dailyathon

import android.widget.Adapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.recycler_view_news.view.*

class NewsItems: Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardViewExampleNews
    }

    override fun getLayout(): Int {
        return R.layout.recycler_view_news
    }
}