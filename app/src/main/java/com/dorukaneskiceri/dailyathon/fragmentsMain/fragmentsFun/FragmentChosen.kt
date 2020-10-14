package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsFun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.items.ChosenItems
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_chosen.*

class FragmentChosen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chosen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewChosen.layoutManager = LinearLayoutManager(view.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewChosen.adapter = adapter

        for(i in 1..10){
            adapter.add(ChosenItems())
        }
    }
}