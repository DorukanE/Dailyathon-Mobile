package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewSurveysBinding
import com.dorukaneskiceri.dailyathon.model.UserSurveyListModel

class RecyclerAdapterSurveys(
    private val arrayListSurvey: ArrayList<UserSurveyListModel>,
    private val startDate: String,
    private val dueDate: String
): RecyclerView.Adapter<RecyclerAdapterSurveys.SurveyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewSurveysBinding>(inflater, R.layout.recycler_view_surveys, parent, false)
        return SurveyHolder(view)
    }

    override fun onBindViewHolder(holder: SurveyHolder, position: Int) {
        holder.view.textView24.text = startDate
        holder.view.textView25.text = dueDate
        holder.view.surveys = arrayListSurvey.get(position)
    }

    override fun getItemCount(): Int {
        return arrayListSurvey.size
    }

    class SurveyHolder(var view: RecyclerViewSurveysBinding): RecyclerView.ViewHolder(view.root){

    }
}