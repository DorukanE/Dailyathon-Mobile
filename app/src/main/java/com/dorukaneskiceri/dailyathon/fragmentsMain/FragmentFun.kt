package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.ViewPagerAdapterFun
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsFun.FragmentChosen
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsFun.FragmentChosenCity
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsFun.FragmentSearch
import kotlinx.android.synthetic.main.fragment_fun.*

class FragmentFun : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager(viewPagerFun)
        tabLayoutFun.setupWithViewPager(viewPagerFun)
    }

    private fun setUpViewPager(viewPagerFun: ViewPager?) {
        val adapter = ViewPagerAdapterFun(childFragmentManager)
        adapter.addFragment(FragmentChosen(),"Sizin Seçtikleriniz")
        adapter.addFragment(FragmentChosenCity(),"Şehrinize Göre")
        adapter.addFragment(FragmentSearch(),"Arama Yapın")
        viewPagerFun?.adapter = adapter
    }


}