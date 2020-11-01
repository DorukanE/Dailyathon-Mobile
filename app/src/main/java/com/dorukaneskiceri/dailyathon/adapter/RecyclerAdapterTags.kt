package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R

class RecyclerAdapterTags: RecyclerView.Adapter<RecyclerAdapterTags.TagsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_tags, parent, false)
        return TagsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.buttonTags.text = "Yazılım"
        holder.buttonTags.textOn = null
        holder.buttonTags.textOff = null
    }

    override fun getItemCount(): Int {
        return 5
    }

    class TagsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val buttonTags: ToggleButton = view.findViewById(R.id.buttonTags)
    }
}