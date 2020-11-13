package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsNews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.FragmentDailyNewsDetailBinding
import kotlinx.android.synthetic.main.fragment_daily_news_detail.*

class FragmentDailyNewsDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentDailyNewsDetailDirections.actionFragmentDailyNewsDetailToFragmentDailyNews()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        arguments?.let {
            val newsTitle = FragmentDailyNewsDetailArgs.fromBundle(it).newsTitle
            val newsDescription = FragmentDailyNewsDetailArgs.fromBundle(it).newsDescription
            val newsType = FragmentDailyNewsDetailArgs.fromBundle(it).newsType

            textViewDailyTitle.text = newsTitle
            textViewDailyDescription.text = newsDescription
            textViewDailyTag.text = newsType
        }

        imageViewBackDaily.setOnClickListener {
            val action =
                FragmentDailyNewsDetailDirections.actionFragmentDailyNewsDetailToFragmentDailyNews()
            Navigation.findNavController(it).navigate(action)
        }
    }
}