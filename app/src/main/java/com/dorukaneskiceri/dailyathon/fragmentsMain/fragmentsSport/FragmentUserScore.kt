package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsSport

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterScores
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterUserScore
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentNewsDirections
import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel
import com.dorukaneskiceri.dailyathon.view_model.ScoreListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLeagueListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_score_table.*
import kotlinx.android.synthetic.main.fragment_user_score.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentUserScore : Fragment() {

    private lateinit var leagueTableName: String
    private lateinit var userScoreTable: String
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelUserScoreList: UserLeagueListViewModel
    private var adapter: RecyclerAdapterUserScore? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            leagueTableName = FragmentUserScoreArgs.fromBundle(it).leagueTableName
            userScoreTable = FragmentUserScoreArgs.fromBundle(it).userSportName
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentUserScoreDirections.actionFragmentUserScoreToFragmentUserSport()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelUserScoreList = ViewModelProvider(this).get(UserLeagueListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)
        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", MODE_PRIVATE)
        val sharedPreferencesUserID: SharedPreferences =
            requireActivity().getSharedPreferences("userID", MODE_PRIVATE)

        recycler_view_user_score.layoutManager = LinearLayoutManager(view.context)
        val arrayListUserScore = ArrayList<UserLeagueListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getUser(userEmail!!, userPassword!!, sharedPreferencesToken, sharedPreferencesUserID)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            val userID = sharedPreferencesUserID.getInt("userID", 0)
            getScoreList(arrayListUserScore, token!!, userID, userScoreTable)
        }

        imageView24.setOnClickListener {
            val action = FragmentUserScoreDirections.actionFragmentUserScoreToFragmentUserSport()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getScoreList(
        arrayListUserScore: java.util.ArrayList<UserLeagueListModel>,
        token: String,
        userID: Int,
        userScoreTable: String
    ) {
        viewModelUserScoreList.getUserLeagues(token, userID, userScoreTable)
        viewModelUserScoreList.leagueList.observe(viewLifecycleOwner, {response ->
            arrayListUserScore.add(response)
            adapter = RecyclerAdapterUserScore(arrayListUserScore)
            recycler_view_user_score.adapter = adapter
            progressBar17.visibility = View.INVISIBLE
        })
    }

    private fun getUser(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences,
        sharedPreferencesUserID: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, {response ->
            val userID = response.userInformation.userId
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
            sharedPreferencesUserID.edit().putInt("userID", userID).apply()
        })
    }
}