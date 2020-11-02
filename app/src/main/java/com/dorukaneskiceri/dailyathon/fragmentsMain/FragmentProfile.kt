package com.dorukaneskiceri.dailyathon.fragmentsMain

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.adapter.RecyclerAdapterProfile
import com.dorukaneskiceri.dailyathon.login_signup.LoginActivity
import com.dorukaneskiceri.dailyathon.model.api_model.CategoryListModel
import com.dorukaneskiceri.dailyathon.view_model.CategoryListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_profile.*

class FragmentProfile : Fragment() {

    private lateinit var viewModelCategory: CategoryListViewModel
    var adapter: RecyclerAdapterProfile? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelCategory = ViewModelProvider(this).get(CategoryListViewModel::class.java)

        val arrayListCategory = ArrayList<CategoryListModel>()
        recyclerViewProfile.layoutManager = LinearLayoutManager(view.context)

        showNavigationBar()
        listCategories(view.context, arrayListCategory)

        updateText.setOnClickListener {
            hideNavigationBar()
            val action = FragmentProfileDirections.actionDestinationProfileToFragmentUpdateProfile()
            Navigation.findNavController(it).navigate(action)
        }

        doExitText.setOnClickListener{
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("com.dorukaneskiceri.dailyathon", MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("userIsLogin", false).apply()
            val intent = Intent(it.context, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun hideNavigationBar() {
        val navigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        navigationBar.visibility = View.GONE
    }

    private fun showNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if(bottomNavigationBar.visibility == View.GONE){
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }

    private fun listCategories(context: Context, arrayListCategory: ArrayList<CategoryListModel>){
        viewModelCategory.getCategories()
        viewModelCategory.categoryList.observe(viewLifecycleOwner, { response ->
            println("okundu")
            arrayListCategory.add(response)
            val adapter = RecyclerAdapterProfile(context, arrayListCategory)
            recyclerViewProfile.adapter = adapter
            progressBar2.visibility = View.INVISIBLE
        })
    }

}