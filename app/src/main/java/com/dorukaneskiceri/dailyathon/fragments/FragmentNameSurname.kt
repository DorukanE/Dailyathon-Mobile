package com.dorukaneskiceri.dailyathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.SignUpActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_name_surname.*

class FragmentNameSurname : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_name_surname, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNameSurname.setOnClickListener {
            val action = FragmentNameSurnameDirections.actionFragmentNameSurnameToFragmentBirthJobCity()
            Navigation.findNavController(it).navigate(action)
        }

        backButton.setOnClickListener {
            val action = FragmentNameSurnameDirections.actionFragmentNameSurnameToLoginSignUpActivity()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
