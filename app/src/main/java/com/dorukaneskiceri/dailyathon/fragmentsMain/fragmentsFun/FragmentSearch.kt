package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsFun

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dorukaneskiceri.dailyathon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*

class FragmentSearch : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewStartDate.setOnClickListener {
            showDateDialogStart(it)
        }

        textViewEndDate.setOnClickListener {
            showDateDialogEnd(it)
        }
    }

    private fun showDateDialogStart(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            val savedString = "$i3 / ${i2 + 1} / $i"
            textViewStartDate.setTextColor(ContextCompat.getColor(view.context,R.color.colorWhite))
            textViewStartDate.text = "Başlangıç: $savedString"
        },year,month,day).show()
    }

    private fun showDateDialogEnd(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            val savedString = "$i3 / ${i2 + 1} / $i"
            textViewEndDate.setTextColor(ContextCompat.getColor(view.context,R.color.colorWhite))
            textViewEndDate.text = "Bitiş: $savedString"
        },year,month,day).show()
    }
}