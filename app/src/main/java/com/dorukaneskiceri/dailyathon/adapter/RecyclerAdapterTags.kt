package com.dorukaneskiceri.dailyathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.databinding.RecyclerViewTagsBinding
import com.dorukaneskiceri.dailyathon.model.api_model.CategoryListModel
import com.dorukaneskiceri.dailyathon.model.api_model.UserTagListModel

class RecyclerAdapterTags(
    private val arrayListTags: ArrayList<UserTagListModel>,
    private val arrayListCategory: ArrayList<CategoryListModel>
) : RecyclerView.Adapter<RecyclerAdapterTags.TagsViewHolder>() {

    private var hashMap = HashMap<Any, ArrayList<UserTagListModel>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerViewTagsBinding>(
            inflater,
            R.layout.recycler_view_tags,
            parent,
            false
        )
        return TagsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        //showTags()
        //getTags(holder)
//
//        holder.view.userTags = hashMap.get(arrayListCategory.get(position).categoryName)

            val variable = hashMap.getValue(arrayListCategory.get(position).categoryName).size
            for(i in 0..variable-1){
                val view = hashMap.getValue(arrayListCategory.get(position).categoryName).get(i)
                holder.view.userTags = view
            }

//        println(hashMap.get(arrayListCategory.get(position).categoryName)?.get(position))
//        holder.view.buttonTags.textOff = null
//        holder.view.buttonTags.textOn = null

    }

//    private fun getTags(holder: TagsViewHolder) {
//        hashMap.getValue().forEach {
//            println(it)
//            //holder.view.userTags = it
//        }
//    }

    override fun getItemCount(): Int {
        showTags()
        var count = 0
//        for(keys in hashMap.keys){
//                hashMap.getValue("Haber").get()
//        }
        return hashMap.size
    }

    class TagsViewHolder(var view: RecyclerViewTagsBinding) :
        RecyclerView.ViewHolder(view.root) {

    }

    private fun showTags() {
        for (i in arrayListCategory) {
            val tagList = ArrayList<UserTagListModel>()
            for (j in arrayListTags) {
                if (i.categoryID == j.categoryID) {
                    tagList.add(j)
                }
            }
            hashMap.put(i.categoryName, tagList)
        }
    }
}