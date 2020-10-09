package com.dorukaneskiceri.dailyathon

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.recycler_view_tags.view.*

class TagsItems: Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        editButtonTags(viewHolder)
        viewHolder.itemView.buttonTags.setOnClickListener {
            if(viewHolder.itemView.buttonTags.isChecked){
                println("selected")
            }
            else{
                println("not selected")
            }
        }

    }

    override fun getLayout(): Int {
        return R.layout.recycler_view_tags
    }


    private fun editButtonTags(viewHolder: GroupieViewHolder) {
        viewHolder.itemView.buttonTags.text = "Yazılım"
        viewHolder.itemView.buttonTags.textOff = null
        viewHolder.itemView.buttonTags.textOn = null

    }
}