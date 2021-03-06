package com.dorukaneskiceri.dailyathon.fragmentsSignup

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.view_model.UserSignUpViewModel
import kotlinx.android.synthetic.main.fragment_birth_job_city.*
import java.util.*

class FragmentBirthJobCity : Fragment() {

    private lateinit var cityArray: Array<String>
    private lateinit var userName: String
    private lateinit var userSurname: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_birth_job_city, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            userName = FragmentBirthJobCityArgs.fromBundle(it).userName
            userSurname = FragmentBirthJobCityArgs.fromBundle(it).userSurname
        }



        birthJobCityButton.setOnClickListener {
            val userBirth = textViewBirth.text.toString()
            val userJob = textInputJob.editText!!.text.toString()
            val userCity = autoCompleteTextViewCity.text.toString()
            if (userBirth.isBlank() || userJob.isBlank() || userCity.isBlank()) {
                Toast.makeText(
                    view.context,
                    "Lütfen Doğum Tarihi, Meslek ve Şehir Değerlerini Giriniz.",
                    Toast.LENGTH_LONG
                ).show()
            } else {


                val action =
                    FragmentBirthJobCityDirections.actionFragmentBirthJobCityToFragmentEmailPassword(
                        userName,
                        userSurname,
                        userBirth,
                        userJob,
                        userCity
                    )
                Navigation.findNavController(it).navigate(action)
            }
        }

        textViewBirth.setOnClickListener {
            showDateDialog(it)
        }

        getCitiesFromDatabase(view)

    }

    private fun getCitiesFromDatabase(view: View) {
        cityArray = arrayOf("Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce")
        val adapter =
            ArrayAdapter(view.context, R.layout.custom_list_view, R.id.customViewCity, cityArray)
        autoCompleteTextViewCity.setAdapter(adapter)
    }

    private fun showDateDialog(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(view.context, { datePicker, i, i2, i3 ->
            val savedString = "$i-${i2 + 1}-$i3"
            textViewBirth.setTextColor(ContextCompat.getColor(view.context, R.color.colorWhite))
            textViewBirth.text = savedString
        }, year, month, day).show()
    }


}