package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewTagsBinding
import com.dorukaneskiceri.dailyathon.model.UserTagListModel

class RecyclerAdapterUserTags(
    private val arrayListTags: ArrayList<UserTagListModel>,
    private val categoryName: String
) : RecyclerView.Adapter<RecyclerAdapterUserTags.UserTagsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserTagsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewTagsBinding>(
            inflater,
            R.layout.recycler_view_tags,
            parent,
            false
        )
        return UserTagsHolder(view)
    }

    override fun onBindViewHolder(holder: UserTagsHolder, position: Int) {
        holder.view.userTags = arrayListTags.get(position)
        holder.view.buttonTags.textOff = null
        holder.view.buttonTags.textOn = null
    }

    override fun getItemCount(): Int {
        return arrayListTags.size
    }

    class UserTagsHolder(var view: RecyclerViewTagsBinding) : RecyclerView.ViewHolder(view.root) {

    }
}