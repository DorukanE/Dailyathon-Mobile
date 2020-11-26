package com.dorukaneskiceri.dailyathon.items

import com.dorukaneskiceri.dailyathon.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.recycler_view_tags_final.view.*

class TagsItemsFinal: Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        editButtonTags(viewHolder)
    }

    override fun getLayout(): Int {
        return R.layout.recycler_view_tags_final
    }

    private fun editButtonTags(viewHolder: GroupieViewHolder) {
        viewHolder.itemView.buttonTagsFinal.text = "Emre Aydın"
        viewHolder.itemView.buttonTagsFinal.textOff = null
        viewHolder.itemView.buttonTagsFinal.textOn = null

        viewHolder.itemView.buttonTagsFinal2.text = "Formula 1"
        viewHolder.itemView.buttonTagsFinal2.textOff = null
        viewHolder.itemView.buttonTagsFinal2.textOn = null

        viewHolder.itemView.buttonTagsFinal3.text = "God Of War"
        viewHolder.itemView.buttonTagsFinal3.textOn = null
        viewHolder.itemView.buttonTagsFinal3.textOff = null

    }
}