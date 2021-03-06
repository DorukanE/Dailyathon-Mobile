package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewAnnouncementBinding
import com.dorukaneskiceri.dailyathon.model.UserAnnouncementListModel

class RecyclerAdapterAnnouncement(
    private val arrayListAnnouncement: ArrayList<UserAnnouncementListModel>,
    private val startDate: String
): RecyclerView.Adapter<RecyclerAdapterAnnouncement.AnnouncementHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewAnnouncementBinding>(inflater, R.layout.recycler_view_announcement, parent, false)
        return AnnouncementHolder(view)
    }

    override fun onBindViewHolder(holder: AnnouncementHolder, position: Int) {
        holder.view.textViewADate.text = startDate
        holder.view.announcement = arrayListAnnouncement.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListAnnouncement.size
    }


    class AnnouncementHolder(var view: RecyclerViewAnnouncementBinding): RecyclerView.ViewHolder(view.root){

    }
}