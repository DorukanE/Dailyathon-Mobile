package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferencesUserName: SharedPreferences =
            requireActivity().getSharedPreferences("userName", MODE_PRIVATE)
        val name = sharedPreferencesUserName.getString("name", "")

        textViewMessage.text = "Günaydın ${name}"

        showNavigationBar()

        cardViewAnnouncement.setOnClickListener {
            val action = FragmentHomeDirections.actionDestinationHomeToFragmentAnnouncement()
            Navigation.findNavController(it).navigate(action)
        }

        cardViewSurveys.setOnClickListener {
            val action = FragmentHomeDirections.actionDestinationHomeToFragmentSurvey()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun showNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if(bottomNavigationBar.visibility == View.GONE){
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }
}