package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsAnnouncement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.items.AnnouncementItems
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_announcement.*

class FragmentAnnouncement : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_announcement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButtonAnnouncement.setOnClickListener {
            val action = FragmentAnnouncementDirections.actionFragmentAnnouncementToDestinationHome()
            Navigation.findNavController(it).navigate(action)
        }

        recyclerViewAnnouncement.layoutManager = LinearLayoutManager(view.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewAnnouncement.adapter = adapter

        adapter.add(AnnouncementItems())
        adapter.add(AnnouncementItems())
        adapter.add(AnnouncementItems())
    }
}