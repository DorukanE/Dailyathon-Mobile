package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsPharmacy.FragmentPharmacyDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_dailyathon.*


class FragmentDailyathon : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dailyathon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentDailyathonDirections.actionDestinationDailyathonToDestinationHome()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        cardViewPharmacy.setOnClickListener {
            hideNavigationBar()
            val action = FragmentDailyathonDirections.actionDestinationDailyathonToFragmentPharmacy()
            Navigation.findNavController(it).navigate(action)
        }

        cardViewCurrency.setOnClickListener {
            hideNavigationBar()
            val action = FragmentDailyathonDirections.actionDestinationDailyathonToFragmentCurrency()
            Navigation.findNavController(it).navigate(action)
        }

        cardViewMovit.setOnClickListener {
            openMovitPage()
        }
    }

    private fun openMovitPage() {
        try {
            val packageManager = view?.context?.packageManager
            packageManager?.getPackageInfo("com.tranzmate", PackageManager.GET_ACTIVITIES)
            //"moovit://directions?dest_lat=&dest_lon=&dest_name=&orig_lat=&orig_lon=&orig_name=&date=2020-10-23T17:48:00+03:00&partner_id=com.dorukaneskiceri.dailyathon"
            val uri = "moovit://nearby?lat=&lon=&partner_id=com.dorukaneskiceri.dailyathon>"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uri)
            startActivity(intent)
        } catch (e: PackageManager.NameNotFoundException) {
            val url = "http://app.appsflyer.com/com.tranzmate?pid=DL&c=com.dorukaneskiceri.dailyathon"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
    }

    private fun hideNavigationBar() {
        val bottomNavigationBar =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        bottomNavigationBar.visibility = View.GONE
    }

}