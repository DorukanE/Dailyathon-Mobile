package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewChosenCityBinding
import com.dorukaneskiceri.dailyathon.model.UserEntertainmentModel

class RecyclerAdapterCityEntertainment(
    private val arrayListCityEntertainment: ArrayList<UserEntertainmentModel>,
    private val startDate: String,
    private val dueDate: String
): RecyclerView.Adapter<RecyclerAdapterCityEntertainment.CityEntertainmentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityEntertainmentHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewChosenCityBinding>(inflater, R.layout.recycler_view_chosen_city, parent, false)
        return CityEntertainmentHolder(view)
    }

    override fun onBindViewHolder(holder: CityEntertainmentHolder, position: Int) {
        holder.view.textViewStartDateCity.text = startDate
        holder.view.textViewDueDateCity.text = dueDate
        holder.view.city = arrayListCityEntertainment.get(position)
    }

    override fun getItemCount(): Int {
       return arrayListCityEntertainment.size
    }

    class CityEntertainmentHolder(var view: RecyclerViewChosenCityBinding): RecyclerView.ViewHolder(view.root){

    }
}