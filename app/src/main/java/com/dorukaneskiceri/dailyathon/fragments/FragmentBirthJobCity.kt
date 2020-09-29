package com.dorukaneskiceri.dailyathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.fragment_birth_job_city.*

class FragmentBirthJobCity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_birth_job_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bjcButton.setOnClickListener {
            val action = FragmentBirthJobCityDirections.actionFragmentBirthJobCityToFragmentEmailPassword()
            Navigation.findNavController(it).navigate(action)
        }
    }

}