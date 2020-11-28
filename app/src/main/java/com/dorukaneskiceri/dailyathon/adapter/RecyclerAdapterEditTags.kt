package com.dorukaneskiceri.dailyathon.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.model.CategoryTagModel
import com.dorukaneskiceri.dailyathon.model.UserTagListModel
import kotlinx.android.synthetic.main.fragment_edit_tags.view.*
import kotlinx.android.synthetic.main.recycler_view_edit_tags.view.*
import kotlinx.android.synthetic.main.recycler_view_tags.view.*

class RecyclerAdapterEditTags(
    private val arrayListTags: ArrayList<CategoryTagModel>,
    private val view: View,
    private val userTags: ArrayList<UserTagListModel>
) :
    RecyclerView.Adapter<RecyclerAdapterEditTags.EditTagsHolder>() {

    private var arrayListSelected = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTagsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_edit_tags, parent, false)
        return EditTagsHolder(view)
    }

    override fun onBindViewHolder(holder: EditTagsHolder, position: Int) {
        userTags.forEach {
            if(it.tagName == arrayListTags.get(position).tagName){
                holder.view.buttonEditTags.isChecked = true
                holder.view.buttonEditTags.setBackgroundResource(R.drawable.button_selected)
            }
        }

        holder.view.buttonEditTags.text = arrayListTags.get(position).tagName
        holder.view.buttonEditTags.textOn = null
        holder.view.buttonEditTags.textOff = null

        holder.view.buttonEditTags.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                if (!arrayListSelected.contains(compoundButton.text)) {
                    arrayListSelected.add(compoundButton.text.toString())
                }
            } else {

            }
        }
        view.imageViewSaveTags.setOnClickListener {
            println(arrayListSelected)
        }
    }

    override fun getItemCount(): Int {
        return arrayListTags.size
    }

    class EditTagsHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }
}