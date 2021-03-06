package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.ViewPagerAdapterCurrency
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsNews.FragmentPersonalNewsDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_currency.*

class FragmentCurrency : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentCurrencyDirections.actionFragmentCurrencyToDestinationDailyathon()
                    Navigation.findNavController(view).navigate(action)
                    showNavigationBar()
                }
            })

        imageButtonBackCurrency.setOnClickListener {
            showNavigationBar()
            val action = FragmentCurrencyDirections.actionFragmentCurrencyToDestinationDailyathon()
            Navigation.findNavController(it).navigate(action)
        }

        setUpViewPager(viewPager)
        tabLayoutCurrency.setupWithViewPager(viewPager)
    }

    private fun showNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if(bottomNavigationBar.visibility == View.GONE){
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }

    private fun setUpViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapterCurrency(childFragmentManager)
        adapter.addFragment(FragmentCurrencyPager(),"Borsa")
        adapter.addFragment(FragmentCurrencyCrypto(),"Kripto Para")
        adapter.addFragment(FragmentCurrencyStock(),"Hisse Senedi")
        viewPager?.adapter = adapter
    }


}