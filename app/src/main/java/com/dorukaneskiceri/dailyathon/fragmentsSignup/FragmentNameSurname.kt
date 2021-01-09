package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
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

        nameSurnameButton.setOnClickListener {
            val userName = textInputName.editText!!.text.toString()
            val userSurname = textInputSurname.editText!!.text.toString()
            if (userName.isBlank() || userSurname.isBlank()) {
                Toast.makeText(
                    view.context,
                    "Lütfen İsim ve Soyisim Değerlerini Giriniz.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val action =
                    FragmentNameSurnameDirections.actionFragmentNameSurnameToFragmentBirthJobCity(
                        userName,
                        userSurname
                    )
                Navigation.findNavController(it).navigate(action)
            }
        }
        backButton.setOnClickListener {
            val action = FragmentNameSurnameDirections.actionFragmentNameSurnameToLoginActivity2()
            Navigation.findNavController(it).navigate(action)
        }
    }

}
