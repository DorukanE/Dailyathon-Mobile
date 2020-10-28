package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsAnnouncement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.items.AnnouncementItems
import com.dorukaneskiceri.dailyathon.view_model.UserAnnouncementListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_announcement.*

class FragmentAnnouncement : Fragment() {

    private lateinit var viewModelUserAnnouncements: UserAnnouncementListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_announcement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelUserAnnouncements = ViewModelProvider(this).get(UserAnnouncementListViewModel::class.java)

        getProfileView()

        backButtonAnnouncement.setOnClickListener {
            val action = FragmentAnnouncementDirections.actionFragmentAnnouncementToDestinationHome()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getProfileView() {
        recyclerViewAnnouncement.layoutManager = LinearLayoutManager(view?.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewAnnouncement.adapter = adapter
        listAnnouncements(adapter)
    }

    private fun listAnnouncements(adapter: GroupAdapter<GroupieViewHolder>) {
        viewModelUserAnnouncements.getUserAnnouncements()
        viewModelUserAnnouncements.announcementList.observe(viewLifecycleOwner, { response ->
            val title = response.announcementTitle
            val description = response.announcementContent
            val date = response.announcementDate
            adapter.add(AnnouncementItems(title, description, date))
        })
    }

}