package com.dorukaneskiceri.dailyathon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewProfileBinding
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentProfileDirections
import com.dorukaneskiceri.dailyathon.model.api_model.CategoryListModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserTagListModel
import kotlinx.android.synthetic.main.recycler_view_profile.view.*

class RecyclerAdapterProfile(
    private val context: Context,
    private val arrayListCategories: ArrayList<CategoryListModel>,
    private val arrayListTags: ArrayList<UserTagListModel>
) : RecyclerView.Adapter<RecyclerAdapterProfile.ProfileViewHolder>(), CategoryClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewProfileBinding>(
            inflater,
            R.layout.recycler_view_profile,
            parent,
            false
        )
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.view.textViewCategoryID.text = arrayListCategories.get(position).categoryID.toString()
        holder.view.listener = this
        holder.view.textViewCategory.text = arrayListCategories.get(position).categoryName

    }

    override fun getItemCount(): Int {
        return arrayListCategories.size
    }

    class ProfileViewHolder(val view: RecyclerViewProfileBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    override fun onCategoryClicked(it: View) {
        val categoryName = it.textViewCategory.text.toString()
        val categoryID = it.textViewCategoryID.text.toString().toInt()
        val action = FragmentProfileDirections.actionDestinationProfileToFragmentProfileScreen(categoryName, categoryID)
        Navigation.findNavController(it).navigate(action)
    }
}