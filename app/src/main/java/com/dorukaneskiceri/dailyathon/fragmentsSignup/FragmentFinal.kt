package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterFinalTags
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserSignUpViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserTagSelectViewModel
import kotlinx.android.synthetic.main.fragment_final.*

import java.lang.Exception

class FragmentFinal : Fragment() {
    private lateinit var userName: String
    private lateinit var userSurname: String
    private lateinit var userBirth: String
    private lateinit var userJob: String
    private lateinit var userCity: String
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var tagsFinal: ArrayList<String>
    private lateinit var adapter: RecyclerAdapterFinalTags
    private lateinit var viewModelTagSelect: UserTagSelectViewModel

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
            tagsFinal = FragmentFinalArgs.fromBundle(it).tagsFinal.arrayListTags
            textViewWelcome.text = "Hoşgeldin $userName"
        }

        viewModelTagSelect = ViewModelProvider(this).get(UserTagSelectViewModel::class.java)

        val sharedPreferencesToken: SharedPreferences = requireActivity().getSharedPreferences(
            "userToken",
            MODE_PRIVATE
        )
        val sharedPreferencesUserID: SharedPreferences = requireActivity().getSharedPreferences(
            "userID",
            MODE_PRIVATE
        )

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
            Toast.makeText(
                it.context,
                "Kayıt Tamamlanıyor, Lütfen Bekleyiniz..",
                Toast.LENGTH_LONG
            ).show()

            Handler().postDelayed({
                try {
                    val token = sharedPreferencesToken.getString("token", "")
                    val userID = sharedPreferencesUserID.getInt("userID", 0)
                    tagsFinal.forEach {
                        viewModelTagSelect.saveUserTags(token!!, userID, it)
                        viewModelTagSelect.selectTags.observe(viewLifecycleOwner, { response ->
                            println(response.message)
                        })
                    }
                    val action = FragmentFinalDirections.actionFragmentFinalToLoginActivity()
                    Navigation.findNavController(it).navigate(action)

                } catch (e: Exception) {
                    println(e.printStackTrace())
                    Toast.makeText(
                        it.context,
                        "Lütfen İnternet Bağlantınızı Kontrol Ediniz",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }, 1000)
        }

        recyclerViewFinalTags.layoutManager = LinearLayoutManager(view.context)
        tagsFinal.forEach {
            adapter = RecyclerAdapterFinalTags(tagsFinal)
            recyclerViewFinalTags.adapter = adapter
        }
    }


}