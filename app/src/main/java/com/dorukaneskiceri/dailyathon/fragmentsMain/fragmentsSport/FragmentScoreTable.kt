package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsSport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.fragment_score_table.*

class FragmentScoreTable : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val leagueName = FragmentScoreTableArgs.fromBundle(it).leagueName
            textViewSportNameScore.text = leagueName
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentScoreTableDirections.actionFragmentScoreTableToFragmentLeagueList()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        imageView21.setOnClickListener {
            val action = FragmentScoreTableDirections.actionFragmentScoreTableToFragmentLeagueList()
            Navigation.findNavController(it).navigate(action)
        }
    }
}