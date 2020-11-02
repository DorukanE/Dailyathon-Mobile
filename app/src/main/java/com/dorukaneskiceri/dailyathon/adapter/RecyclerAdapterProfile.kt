package com.dorukaneskiceri.dailyathon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewProfileBinding
import com.dorukaneskiceri.dailyathon.model.api_model.CategoryListModel
import kotlinx.android.synthetic.main.recycler_view_profile.view.*


class RecyclerAdapterProfile(private val context: Context, private val arrayList: ArrayList<CategoryListModel>): RecyclerView.Adapter<RecyclerAdapterProfile.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.recycler_view_profile,parent,false)
        val view = DataBindingUtil.inflate<RecyclerViewProfileBinding>(inflater, R.layout.recycler_view_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.view.tagCategories = arrayList.get(position)
        holder.view.recyclerViewTags.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.view.recyclerViewTags.adapter = RecyclerAdapterTags()

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ProfileViewHolder(val view: RecyclerViewProfileBinding): RecyclerView.ViewHolder(view.root){

    }
}