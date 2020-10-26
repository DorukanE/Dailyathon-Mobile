package com.dorukaneskiceri.dailyathon.items

import com.dorukaneskiceri.dailyathon.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.recycler_view_tags_signup.view.*

class TagsItemsSignUp: Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        editButtonTags(viewHolder)
    }

    override fun getLayout(): Int {
        return R.layout.recycler_view_tags_signup
    }

    private fun editButtonTags(viewHolder: GroupieViewHolder) {
        viewHolder.itemView.buttonTagsSignUp.text = "Mekatronik"
        viewHolder.itemView.buttonTagsSignUp.textOff = null
        viewHolder.itemView.buttonTagsSignUp.textOn = null

        viewHolder.itemView.buttonTagsSignUp2.text = "Bilgisayar Mühendisliği"
        viewHolder.itemView.buttonTagsSignUp2.textOn = null
        viewHolder.itemView.buttonTagsSignUp2.textOff = null

        viewHolder.itemView.buttonTagsSignUp3.text = "Magazin"
        viewHolder.itemView.buttonTagsSignUp3.textOff = null
        viewHolder.itemView.buttonTagsSignUp3.textOn = null
    }
}