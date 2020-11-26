package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewProfileBinding
import com.dorukaneskiceri.dailyathon.fragmentsMain.FragmentProfileDirections
import com.dorukaneskiceri.dailyathon.model.CategoryListModel
import kotlinx.android.synthetic.main.recycler_view_profile.view.*

class RecyclerAdapterProfile(
    private val arrayListCategories: ArrayList<CategoryListModel>,
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
        val action = FragmentProfileDirections.actionDestinationProfileToFragmentProfileScreen(categoryName)
        Navigation.findNavController(it).navigate(action)
    }
}