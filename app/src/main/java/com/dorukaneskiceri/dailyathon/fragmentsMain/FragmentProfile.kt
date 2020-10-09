package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.ProfileItems
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.TagsItems
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.recycler_view_profile.*

class FragmentProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProfileView()

        saveText.setOnClickListener {
            Toast.makeText(view.context,"Değişikler kaydedildi.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun getProfileView(){
        recyclerViewProfile.layoutManager = LinearLayoutManager(view?.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewProfile.adapter = adapter

        adapter.add(ProfileItems())
        adapter.add(ProfileItems())
        adapter.add(ProfileItems())
        adapter.add(ProfileItems())
        adapter.add(ProfileItems())
    }

}