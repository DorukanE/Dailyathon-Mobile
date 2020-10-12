package com.dorukaneskiceri.dailyathon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.activity_main_app.*

class MainAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)

        val navController = Navigation.findNavController(this, R.id.nav_host_main)

        setUpBottomNavigation(navController)
    }

    private fun setUpBottomNavigation(navController: NavController) {
        bottomNavMainApp.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }
}