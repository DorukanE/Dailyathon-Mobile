package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsSport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentNewsDirections

class FragmentUserScore : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentUserScoreDirections.actionFragmentUserScoreToFragmentUserSport()
                    Navigation.findNavController(view).navigate(action)
                }
            })
    }
}