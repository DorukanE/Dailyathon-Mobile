package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsPharmacy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.items.PharmacyItems
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_pharmacy.*

class FragmentPharmacy : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pharmacy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageButtonBackCurrency.setOnClickListener {
            val action = FragmentPharmacyDirections.actionFragmentPharmacyToDestinationDailyathon()
            Navigation.findNavController(it).navigate(action)
        }

        recyclerViewPharmacy.layoutManager = LinearLayoutManager(view.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewPharmacy.adapter = adapter

        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
    }

}