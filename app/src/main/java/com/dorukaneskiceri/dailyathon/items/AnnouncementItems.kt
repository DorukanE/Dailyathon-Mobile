package com.dorukaneskiceri.dailyathon.items

import com.dorukaneskiceri.dailyathon.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.recycler_view_announcement.view.*

class AnnouncementItems(private val title: String, private val description: String, private val date: String): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textViewATitle.text = title
        viewHolder.itemView.textViewADescription.text = description
        viewHolder.itemView.textViewADate.text = date
    }

    override fun getLayout(): Int {
        return R.layout.recycler_view_announcement
    }
}