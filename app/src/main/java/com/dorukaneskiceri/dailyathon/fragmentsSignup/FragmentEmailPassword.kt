package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.fragment_email_password.*
import java.lang.Exception

class FragmentEmailPassword : Fragment() {
    private lateinit var userName: String
    private lateinit var userSurname: String
    private lateinit var userBirth: String
    private lateinit var userJob: String
    private lateinit var userCity: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_email_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            userName = FragmentEmailPasswordArgs.fromBundle(it).userName
            userSurname = FragmentEmailPasswordArgs.fromBundle(it).userSurname
            userBirth = FragmentEmailPasswordArgs.fromBundle(it).userBirth
            userJob = FragmentEmailPasswordArgs.fromBundle(it).userJob
            userCity = FragmentEmailPasswordArgs.fromBundle(it).userCity
        }

        emailPasswordButton.setOnClickListener {
            val userEmail = textInputEmail.editText!!.text.toString()
            val userPassword = textInputPassword.editText!!.text.toString()
            val userRePassword = textInputRePassword.editText!!.text.toString()
            if (userEmail.isBlank() || userPassword.isBlank() || userRePassword.isBlank()) {
                Toast.makeText(
                    view.context,
                    "Lütfen E-posta ve Şifre Değerlerini Giriniz.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (userPassword == userRePassword) {
                    if (isValidEmail(userEmail)) {
                        val action =
                            FragmentEmailPasswordDirections.actionFragmentEmailPasswordToFragmentTags(
                                userName,
                                userSurname,
                                userBirth,
                                userJob,
                                userCity,
                                userEmail,
                                userPassword
                            )
                        Navigation.findNavController(it).navigate(action)
                    } else{
                        Toast.makeText(view.context, "Lütfen Geçerli Bir E-posta Adresi Giriniz.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(
                        view.context,
                        "Girilen Şifre Değerleri Eşleşmiyor.",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

        }

    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}