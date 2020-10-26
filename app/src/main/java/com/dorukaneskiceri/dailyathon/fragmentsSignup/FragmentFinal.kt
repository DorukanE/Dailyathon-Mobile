package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.items.TagsItemsFinal
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_final.*

class FragmentFinal() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_final, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finishButton.setOnClickListener {
            val action = FragmentFinalDirections.actionFragmentFinalToLoginActivity()
            Navigation.findNavController(it).navigate(action)
        }

        recyclerViewFinalTags.layoutManager = LinearLayoutManager(view.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewFinalTags.adapter = adapter

        adapter.add(TagsItemsFinal())
        adapter.add(TagsItemsFinal())
        adapter.add(TagsItemsFinal())

    }
}