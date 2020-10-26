package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.items.TagsItemsSignUp
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_tags.*

class FragmentTags : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tagsButton.setOnClickListener {
            val action = FragmentTagsDirections.actionFragmentTagsToFragmentFinal()
            Navigation.findNavController(it).navigate(action)
        }

        recyclerViewTags.layoutManager = LinearLayoutManager(view.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewTags.adapter = adapter

        adapter.add(TagsItemsSignUp())
        adapter.add(TagsItemsSignUp())
        adapter.add(TagsItemsSignUp())
        adapter.add(TagsItemsSignUp())
    }
}