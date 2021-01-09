package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewTagsSignupBinding
import com.dorukaneskiceri.dailyathon.model.TagListModel

class RecyclerAdapterTagsSignUp(private val arrayListTags: ArrayList<TagListModel>): RecyclerView.Adapter<RecyclerAdapterTagsSignUp.TagsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewTagsSignupBinding>(inflater, R.layout.recycler_view_tags_signup, parent, false)
        return TagsHolder(view)
    }

    override fun onBindViewHolder(holder: TagsHolder, position: Int) {
        holder.view.tags = arrayListTags.get(position)
        holder.view.buttonTagsSignUp.textOff = null
        holder.view.buttonTagsSignUp.textOn = null
    }

    override fun getItemCount(): Int {
        return arrayListTags.size
    }

    class TagsHolder(val view: RecyclerViewTagsSignupBinding): RecyclerView.ViewHolder(view.root){

    }
}