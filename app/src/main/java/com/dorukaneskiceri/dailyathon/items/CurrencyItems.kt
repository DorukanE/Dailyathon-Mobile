package com.dorukaneskiceri.dailyathon.items


import android.widget.Filter
import android.widget.Filterable
import com.dorukaneskiceri.dailyathon.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.recycler__view_currency.view.*

class CurrencyItems( var titleList: ArrayList<String>, private val descriptionList: ArrayList<String>, private val number: Int): Item<GroupieViewHolder>(), Filterable {

    var filteredList: MutableList<String> = mutableListOf()
    var titleListFiltered = titleList
    private var descriptionListFiltered = descriptionList

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textViewTitle.text = titleList[number]
        viewHolder.itemView.textViewDescription.text = descriptionList[number]
        println(descriptionListFiltered)

    }

    override fun getLayout(): Int {
        return R.layout.recycler__view_currency
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString: String = p0.toString()
                if(charString.isEmpty()){
                    titleListFiltered = titleList
                }
                else{

                    for(i in titleList){
                        if(i.toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(i)
                        }
                    }
                    println(filteredList)
                    titleList = filteredList as ArrayList<String>
                }
                var filterResults: FilterResults = FilterResults()
                filterResults.values = titleList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                titleList = p1!!.values as ArrayList<String>
                notifyChanged()
            }

        }
    }
}