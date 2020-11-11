package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsNews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.fragment_personal_news_detail.*

class FragmentPersonalNewsDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentPersonalNewsDetailDirections.actionFragmentPersonalNewsDetailToFragmentPersonalNews()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        imageViewBackPersonal.setOnClickListener {
            val action = FragmentPersonalNewsDetailDirections.actionFragmentPersonalNewsDetailToFragmentPersonalNews()
            Navigation.findNavController(it).navigate(action)
        }
    }
}