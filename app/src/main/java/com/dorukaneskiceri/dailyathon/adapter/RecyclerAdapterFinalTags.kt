package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.recycler_view_tags_final.view.*

class RecyclerAdapterFinalTags(private val arrayListFinalTags: ArrayList<String>): RecyclerView.Adapter<RecyclerAdapterFinalTags.FinalTagsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinalTagsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_tags_final, parent, false)
        return FinalTagsHolder(view)
    }

    override fun onBindViewHolder(holder: FinalTagsHolder, position: Int) {
        holder.view.buttonTagsFinal.text = arrayListFinalTags.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListFinalTags.size
    }

    class FinalTagsHolder(val view: View): RecyclerView.ViewHolder(view){

    }
}