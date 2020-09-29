package com.dorukaneskiceri.dailyathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.fragment_name_surname.*

class FragmentNameSurname : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_surname, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNameSurname.setOnClickListener {
            val action = FragmentNameSurnameDirections.actionFragmentNameSurnameToFragmentBirthJobCity()
//            NavController(it.context).navigate(action)
            Navigation.findNavController(it).navigate(action)
        }
    }

}