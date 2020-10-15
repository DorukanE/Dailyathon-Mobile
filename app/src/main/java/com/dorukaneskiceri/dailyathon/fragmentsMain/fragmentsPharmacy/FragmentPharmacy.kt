package com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsPharmacy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.items.PharmacyItems
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.android.synthetic.main.fragment_pharmacy.*
import java.net.URI

class FragmentPharmacy : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pharmacy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var number: String? = ""

        imageButtonBackPharmacy.setOnClickListener {
            showNavigationBar()
            val action = FragmentPharmacyDirections.actionFragmentPharmacyToDestinationDailyathon()
            Navigation.findNavController(it).navigate(action)
        }

        recyclerViewPharmacy.layoutManager = LinearLayoutManager(view.context)
        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerViewPharmacy.adapter = adapter

        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())
        adapter.add(PharmacyItems())

        adapter.setOnItemClickListener { item, view ->
            val dialog = BottomSheetDialog(view.context)
            val view = layoutInflater.inflate(R.layout.dialog_layout,null)
            dialog.setContentView(view)
            dialog.show()

            val textViewCancel: TextView = view.findViewById(R.id.textViewCancel)
            val textViewMakeCall: TextView = view.findViewById(R.id.textViewMakeCall)
            val textViewFind: TextView = view.findViewById(R.id.textViewFind)

            textViewCancel.setOnClickListener {
                dialog.dismiss()
            }
            textViewFind.setOnClickListener {

            }
            textViewMakeCall.setOnClickListener {
                number = "+905319409022"
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
                startActivity(intent)
            }
        }
    }

    private fun showNavigationBar() {
        val bottomNavigationBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMainApp)
        if(bottomNavigationBar.visibility == View.GONE){
            bottomNavigationBar.visibility = View.VISIBLE
        }
    }

}