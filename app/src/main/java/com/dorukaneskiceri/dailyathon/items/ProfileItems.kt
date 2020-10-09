package com.dorukaneskiceri.dailyathon.items

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentProfile
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.recycler_view_profile.view.*

class ProfileItems: Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.recyclerViewTags.layoutManager = LinearLayoutManager(FragmentProfile().context, RecyclerView.HORIZONTAL,false)
        val adapter = GroupAdapter<GroupieViewHolder>()
        viewHolder.itemView.recyclerViewTags.adapter = adapter



        adapter.add(TagsItems())
        adapter.add(TagsItems())
        adapter.add(TagsItems())
        adapter.add(TagsItems())
        adapter.add(TagsItems())
        adapter.add(TagsItems())
        adapter.add(TagsItems())
    }

    override fun getLayout(): Int {
        return R.layout.recycler_view_profile
    }
}