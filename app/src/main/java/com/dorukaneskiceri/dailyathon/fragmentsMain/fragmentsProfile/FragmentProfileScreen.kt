package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentNewsDirections
import kotlinx.android.synthetic.main.fragment_profile_screen.*

class FragmentProfileScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentProfileScreenDirections.actionFragmentProfileScreenToDestinationProfile()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        arguments?.let {
            val categoryName = FragmentProfileScreenArgs.fromBundle(it).categoryName
            val categoryID = FragmentProfileScreenArgs.fromBundle(it).categoryID

            textView20.text = categoryName
        }

        imageView19.setOnClickListener {
            val action = FragmentProfileScreenDirections.actionFragmentProfileScreenToDestinationProfile()
            Navigation.findNavController(it).navigate(action)
        }
    }
}