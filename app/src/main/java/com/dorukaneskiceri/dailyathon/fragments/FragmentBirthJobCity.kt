package com.dorukaneskiceri.dailyathon.fragments

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import kotlinx.android.synthetic.main.fragment_birth_job_city.*
import java.util.*

class FragmentBirthJobCity : Fragment() {

    private lateinit var cityArray: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_birth_job_city, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonBirthJobCity.setOnClickListener {
            val action = FragmentBirthJobCityDirections.actionFragmentBirthJobCityToFragmentEmailPassword()
            Navigation.findNavController(it).navigate(action)
        }

        textViewBirth.setOnClickListener {
            showDateDialog(it)
        }

        getCitiesFromDatabase(view)

    }

    private fun getCitiesFromDatabase(view: View) {
        cityArray = arrayOf("KOo", "Kaa", "Kasdasd", "Konya", "Kkkk", "Kahramanmaraş", "Kocaeli", "Kokok", "Kırşehir", "Kayseri", "Kastamonu")
        val adapter = ArrayAdapter(view.context,R.layout.custom_list_view, R.id.customViewCity, cityArray)
        autoCompleteTextView.setAdapter(adapter)
    }

    private fun showDateDialog(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(view.context,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            val savedString = "$i3 / ${i2 + 1} / $i"
            textViewBirth.setTextColor(ContextCompat.getColor(view.context,R.color.colorWhite))
            textViewBirth.text = savedString
        },year,month,day).show()
    }

}