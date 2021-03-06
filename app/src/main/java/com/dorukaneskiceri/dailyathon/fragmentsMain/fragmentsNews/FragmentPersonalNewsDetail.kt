package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsNews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_daily_news_detail.*
import kotlinx.android.synthetic.main.fragment_personal_news_detail.*
import java.text.SimpleDateFormat

class FragmentPersonalNewsDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentPersonalNewsDetailDirections.actionFragmentPersonalNewsDetailToFragmentPersonalNews()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        arguments?.let {
            val newsTitle = FragmentPersonalNewsDetailArgs.fromBundle(it).newsTitle
            val newsDescription = FragmentPersonalNewsDetailArgs.fromBundle(it).newsDescription
            val newsType = FragmentPersonalNewsDetailArgs.fromBundle(it).newsType
            val newsDate = FragmentPersonalNewsDetailArgs.fromBundle(it).newsDate
            val newsImageUrl = FragmentPersonalNewsDetailArgs.fromBundle(it).newsImageUrl

            textViewPersonalTitle.text = newsTitle
            textViewPersonalDescription.text = newsDescription
            textViewPersonalTag.text = newsType
            textViewPersonalDate.text = newsDate
            Picasso.get().load(newsImageUrl).into(imageView10)
        }

        imageViewBackPersonal.setOnClickListener {
            val action = FragmentPersonalNewsDetailDirections.actionFragmentPersonalNewsDetailToFragmentPersonalNews()
            Navigation.findNavController(it).navigate(action)
        }
    }
}