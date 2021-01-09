package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.fragment_final.*

class FragmentFinal : Fragment() {
    private lateinit var userName: String
    private lateinit var userSurname: String
    private lateinit var userBirth: String
    private lateinit var userJob: String
    private lateinit var userCity: String
    private lateinit var userEmail: String
    private lateinit var userPassword: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_final, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            userName = FragmentFinalArgs.fromBundle(it).userName
            userSurname = FragmentFinalArgs.fromBundle(it).userSurname
            userBirth = FragmentFinalArgs.fromBundle(it).userBirth
            userJob = FragmentFinalArgs.fromBundle(it).userJob
            userCity = FragmentFinalArgs.fromBundle(it).userCity
            userEmail = FragmentFinalArgs.fromBundle(it).userEmail
            userPassword = FragmentFinalArgs.fromBundle(it).userPassword
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentFinalDirections.actionFragmentFinalToFragmentTags(
                            userName,
                            userSurname,
                            userBirth,
                            userJob,
                            userCity,
                            userEmail,
                            userPassword
                        )
                    Navigation.findNavController(view).navigate(action)
                }
            })

        finishButton.setOnClickListener {
            val action = FragmentFinalDirections.actionFragmentFinalToLoginActivity()
            Navigation.findNavController(it).navigate(action)
        }


    }
}