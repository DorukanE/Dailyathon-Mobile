package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsProfile

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_birth_job_city.*
import kotlinx.android.synthetic.main.fragment_update_profile.*
import java.util.*

class FragmentUpdateProfile : Fragment() {

    private lateinit var cityArray: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val action = FragmentUpdateProfileDirections.actionFragmentUpdateProfileToDestinationProfile()
                Navigation.findNavController(view).navigate(action)
            }
        })

        imageViewCancel.setOnClickListener {
            val action = FragmentUpdateProfileDirections.actionFragmentUpdateProfileToDestinationProfile()
            Navigation.findNavController(view).navigate(action)
        }

        imageViewSave.setOnClickListener {
            Snackbar.make(it,"Değişiklikler Kaydedildi", Snackbar.LENGTH_SHORT).show()
        }

        textViewProfileBirth.setOnClickListener{
            showDateDialog(it)
        }

        getCitiesFromDatabase(view)
    }

    private fun getCitiesFromDatabase(view: View) {
        cityArray = arrayOf("Konya", "Kahramanmaraş", "Kocaeli", "Kırşehir", "Kayseri", "Kastamonu")
        val adapter = ArrayAdapter(view.context,R.layout.custom_list_view, R.id.customViewCity, cityArray)
        autoCompleteTextProfileCity.setAdapter(adapter)
    }

    private fun showDateDialog(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(view.context, { datePicker, i, i2, i3 ->
            val savedString = "$i3 / ${i2 + 1} / $i"
            textViewProfileBirth.setTextColor(ContextCompat.getColor(view.context,R.color.colorWhite))
            textViewProfileBirth.text = savedString
        },year,month,day).show()
    }
}