package com.dorukaneskiceri.dailyathon.items

import com.dorukaneskiceri.dailyathon.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class NewsItemsPersonal: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.recycler_view_news
    }
}