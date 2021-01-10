package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserSignUpViewModel
import kotlinx.android.synthetic.main.fragment_email_password.*

class FragmentEmailPassword : Fragment() {
    private lateinit var userName: String
    private lateinit var userSurname: String
    private lateinit var userBirth: String
    private lateinit var userJob: String
    private lateinit var userCity: String
    private lateinit var viewModelSignUp: UserSignUpViewModel
    private lateinit var viewModelUserLogin: UserLoginViewModel

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

        viewModelSignUp = ViewModelProvider(this).get(UserSignUpViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences = requireActivity().getSharedPreferences(
            "userToken",
            MODE_PRIVATE
        )
        val sharedPreferencesUserID: SharedPreferences = requireActivity().getSharedPreferences(
            "userID",
            MODE_PRIVATE
        )

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
                        Toast.makeText(it.context, "Lütfen Bekleyiniz..", Toast.LENGTH_SHORT).show()
                        signUp(userEmail, userPassword)

                        Handler().postDelayed({
                            getUser(
                                userEmail,
                                userPassword,
                                sharedPreferencesToken,
                                sharedPreferencesUserID
                            )
                        }, 1000)
                        Handler().postDelayed({
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
                        }, 2000)
                    } else {
                        Toast.makeText(
                            view.context,
                            "Lütfen Geçerli Bir E-posta Adresi Giriniz.",
                            Toast.LENGTH_LONG
                        ).show()
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

    private fun signUp(userEmail: String, userPassword: String) {
        viewModelSignUp.postUserSignUp(
            userName,
            userSurname,
            userEmail,
            userPassword,
            userBirth,
            userJob,
            userCity
        )
        viewModelSignUp.myUserSignUp.observe(viewLifecycleOwner, { response ->
            println(response.message)
        })
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences,
        sharedPreferencesUserID: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, { response ->
            val token = response.token
            val userID = response.userInformation.userId
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesUserID.edit().putInt("userID", userID).apply()
        })
    }

}