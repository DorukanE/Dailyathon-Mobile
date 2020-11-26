package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.CategoryTagModel
import kotlinx.android.synthetic.main.recycler_view_edit_tags.view.*

class RecyclerAdapterEditTags(private val arrayListTags: ArrayList<CategoryTagModel>) :
    RecyclerView.Adapter<RecyclerAdapterEditTags.EditTagsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTagsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_edit_tags, parent, false)
        return EditTagsHolder(view)
    }

    override fun onBindViewHolder(holder: EditTagsHolder, position: Int) {
        holder.view.buttonEditTags.text = arrayListTags.get(position).tagName
        holder.view.buttonEditTags.textOn = null
        holder.view.buttonEditTags.textOff = null
    }

    override fun getItemCount(): Int {
        return arrayListTags.size
    }

    class EditTagsHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }
}