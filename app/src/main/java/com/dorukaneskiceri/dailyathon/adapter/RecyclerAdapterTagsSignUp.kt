package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewTagsSignupBinding
import com.dorukaneskiceri.dailyathon.fragmentsSignup.FragmentTags
import com.dorukaneskiceri.dailyathon.model.TagListModel
import kotlinx.android.synthetic.main.fragment_tags.view.*
import kotlinx.android.synthetic.main.recycler_view_tags.view.*

class RecyclerAdapterTagsSignUp(
    private val arrayListTags: ArrayList<TagListModel>,
    private val view: View,
    private val userName: String,
    private val userSurname: String,
    private val userBirth: String,
    private val userJob: String,
    private val userCity: String,
    private val userEmail: String,
    private val userPassword: String
): RecyclerView.Adapter<RecyclerAdapterTagsSignUp.TagsHolder>() {

    private var arrayListSelected = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewTagsSignupBinding>(inflater, R.layout.recycler_view_tags_signup, parent, false)
        return TagsHolder(view)
    }

    override fun onBindViewHolder(holder: TagsHolder, position: Int) {
        holder.view.tags = arrayListTags.get(position)
        holder.view.buttonTagsSignUp.textOff = null
        holder.view.buttonTagsSignUp.textOn = null

        holder.view.buttonTagsSignUp.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                if (!arrayListSelected.contains(compoundButton.text)) {
                    arrayListSelected.add(compoundButton.text.toString())
                }
            } else{
                if(arrayListSelected.contains(compoundButton.text)){
                    arrayListSelected.remove(compoundButton.text)
                }
            }
        }

        view.tagsButton.setOnClickListener {
            FragmentTags().getTagsSignUp(arrayListSelected, it, userName, userSurname, userBirth, userJob, userCity, userEmail, userPassword)
        }
    }

    override fun getItemCount(): Int {
        return arrayListTags.size
    }

    class TagsHolder(val view: RecyclerViewTagsSignupBinding): RecyclerView.ViewHolder(view.root){

    }
}