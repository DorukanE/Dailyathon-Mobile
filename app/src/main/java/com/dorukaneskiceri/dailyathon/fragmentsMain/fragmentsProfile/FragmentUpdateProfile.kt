package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsProfile

import android.app.DatePickerDialog
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.FragmentUpdateProfileBinding
import com.dorukaneskiceri.dailyathon.model.UserLoginModel
import com.dorukaneskiceri.dailyathon.view_model.UserLoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_update_profile.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentUpdateProfile : Fragment() {

    private lateinit var cityArray: Array<String>
    private lateinit var viewModelLogin: UserLoginViewModel
    private lateinit var dataBinding: FragmentUpdateProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelLogin = ViewModelProvider(this).get(UserLoginViewModel::class.java)
        val sharedPreferencesMail =
            requireActivity().getSharedPreferences("userEmail", MODE_PRIVATE)
        val sharedPreferencesPassword =
            requireActivity().getSharedPreferences("userPassword", MODE_PRIVATE)

        val userEmail: String? = sharedPreferencesMail.getString("email", "")
        val userPassword: String? = sharedPreferencesPassword.getString("password", "")

        getUserInfos(userEmail!!, userPassword!!)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action =
                        FragmentUpdateProfileDirections.actionFragmentUpdateProfileToDestinationProfile()
                    Navigation.findNavController(view).navigate(action)
                }
            })

        imageViewCancel.setOnClickListener {
            val action =
                FragmentUpdateProfileDirections.actionFragmentUpdateProfileToDestinationProfile()
            Navigation.findNavController(view).navigate(action)
        }

        imageViewSave.setOnClickListener {
            Snackbar.make(it, "Değişiklikler Kaydedildi", Snackbar.LENGTH_SHORT).show()
        }

        textViewProfileBirth.setOnClickListener {
            showDateDialog(it)
        }

        getCitiesFromDatabase(view)
    }

    private fun getUserInfos(email: String, password: String) {
        viewModelLogin.postUserLoginProfile(email, password)
        viewModelLogin.myUserLoginProfile.observe(viewLifecycleOwner, { response ->
            val userDate = getUserProfileDate(response)
            dataBinding.textViewProfileBirth.text = userDate
            dataBinding.userInfo = response

            /*textInputProfileName.editText?.setText(response.userInformation.userName)
            textInputProfileSurname.editText?.setText(response.userInformation.userSurname)
            textViewProfileBirth.text = response.userInformation.userDate
            textInputProfileJob.editText?.setText(response.userInformation.userProfession)
            autoCompleteTextProfileCity.setText(response.userInformation.userCity)
            textInputProfileEmail.editText?.setText(response.userInformation.userMail)
            textInputProfilePassword.editText?.setText(response.userInformation.userPassword)
            textInputProfileAgainPassword.editText?.setText(response.userInformation.userPassword)*/
        })
    }

    private fun getUserProfileDate(response: UserLoginModel): String {
        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(response.userInformation.userDate)
        return outputFormat.format(date)
    }

    private fun getCitiesFromDatabase(view: View) {
        cityArray = arrayOf("Konya", "Kahramanmaraş", "Kocaeli", "Kırşehir", "Kayseri", "Kastamonu")
        val adapter =
            ArrayAdapter(view.context, R.layout.custom_list_view, R.id.customViewCity, cityArray)
        autoCompleteTextProfileCity.setAdapter(adapter)
    }

    private fun showDateDialog(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(view.context, { datePicker, i, i2, i3 ->
            val savedString = "$i3 / ${i2 + 1} / $i"
            textViewProfileBirth.setTextColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.colorWhite
                )
            )
            textViewProfileBirth.text = savedString
        }, year, month, day).show()
    }
}