package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsSport

import android.content.Context
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
import com.dorukaneskiceri.dailyathon.model.UserLeagueListModel
import com.dorukaneskiceri.dailyathon.view_model.ScoreListViewModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_score_table.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class FragmentScoreTable : Fragment() {

    private var leagueID: Int = 0
    private var sportID: Int = 0
    private lateinit var viewModelUserLogin: UserLoginViewModel
    private lateinit var viewModelScoreList: ScoreListViewModel
    private var adapter: RecyclerAdapterScores? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score_table, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val leagueName = FragmentScoreTableArgs.fromBundle(it).leagueName
            textViewSportNameScore.text = leagueName
            leagueID = FragmentScoreTableArgs.fromBundle(it).leagueID
            sportID = FragmentScoreTableArgs.fromBundle(it).sportID
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = FragmentScoreTableDirections.actionFragmentScoreTableToFragmentLeagueList()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        viewModelScoreList = ViewModelProvider(this).get(ScoreListViewModel::class.java)
        viewModelUserLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)

        val sharedPreferencesEmail: SharedPreferences =
            requireActivity().getSharedPreferences("userEmail", Context.MODE_PRIVATE)
        val sharedPreferencesPassword: SharedPreferences =
            requireActivity().getSharedPreferences("userPassword", Context.MODE_PRIVATE)
        val sharedPreferencesToken: SharedPreferences =
            requireActivity().getSharedPreferences("userToken", Context.MODE_PRIVATE)

        recycler_view_score.layoutManager = LinearLayoutManager(view.context)
        val arrayListScore = ArrayList<UserLeagueListModel>()

        val userEmail = sharedPreferencesEmail.getString("email", "")
        val userPassword = sharedPreferencesPassword.getString("password", "")

        runBlocking {
            val function = async {
                getToken(userEmail!!, userPassword!!, sharedPreferencesToken)
            }
            function.await()
            val token = sharedPreferencesToken.getString("token", "")
            getScoreList(arrayListScore, token!!, leagueID, sportID)
        }

        imageView21.setOnClickListener {
            val action = FragmentScoreTableDirections.actionFragmentScoreTableToFragmentLeagueList()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getScoreList(
        arrayListScore: java.util.ArrayList<UserLeagueListModel>,
        token: String,
        leagueID: Int,
        sportID: Int
    ) {
        viewModelScoreList.getScoreList(token, leagueID, sportID)
        viewModelScoreList.scoreList.observe(viewLifecycleOwner, {response ->
            arrayListScore.add(response)
            adapter = RecyclerAdapterScores(arrayListScore)
            recycler_view_score.adapter = adapter
            progressBar16.visibility = View.INVISIBLE
        })
    }

    private fun getToken(
        userEmail: String,
        userPassword: String,
        sharedPreferencesToken: SharedPreferences
    ) {
        viewModelUserLogin.postUserLoginProfile(userEmail, userPassword)
        viewModelUserLogin.myUserLoginProfile.observe(viewLifecycleOwner, {response ->
            val token = response.token
            sharedPreferencesToken.edit().putString("token", token).apply()
        })
    }
}