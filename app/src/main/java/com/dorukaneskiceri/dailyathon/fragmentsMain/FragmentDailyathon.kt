package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_dailyathon.*

class FragmentDailyathon : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dailyathon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardViewPharmacy.setOnClickListener {
            hideNavigationBar()
            val action = FragmentDailyathonDirections.actionDestinationDailyathonToFragmentPharmacy()
            Navigation.findNavController(it).navigate(action)
        }

        cardViewCurrency.setOnClickListener {
            hideNavigationBar()
            val action = FragmentDailyathonDirections.actionDestinationDailyathonToFragmentCurrency()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun hideNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        bottomNavigationBar.visibility = View.GONE
    }

}