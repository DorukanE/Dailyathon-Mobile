package com.dorukaneskiceri.dailyathon

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentDailyathon
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency.FragmentCurrencyCrypto
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency.FragmentCurrencyPager
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency.FragmentCurrencyStock
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsCurrency.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        setUpTabLayout()

        imageButtonBackCurrency.setOnClickListener {
            val intent = Intent(it.context, MainAppActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setUpTabLayout() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentCurrencyPager(),"Borsa")
        adapter.addFragment(FragmentCurrencyCrypto(),"Kripto Para")
        adapter.addFragment(FragmentCurrencyStock(),"Hisse Senedi")
        viewPager.adapter = adapter
        tabLayoutCurrency.setupWithViewPager(viewPager)
    }
}