package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.items.ProfileItems
import com.dorukaneskiceri.dailyathon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_profile.*

class FragmentProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showNavigationBar()
        getProfileView()

        saveText.setOnClickListener {
            Toast.makeText(view.context,"Değişikler kaydedildi.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if(bottomNavigationBar.visibility == View.GONE){
            bottomNavigationBar.visibility = View.VISIBLE
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