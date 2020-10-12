package com.dorukaneskiceri.dailyathon.items

import android.view.LayoutInflater
import com.dorukaneskiceri.dailyathon.R
import com.dorukaneskiceri.dailyathon.fragmentsMain.fragmentsPharmacy.FragmentPharmacy
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.dialog_layout.view.*
import kotlinx.android.synthetic.main.recycler_view_pharmacy.view.*

class PharmacyItems: Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.recycler_view_pharmacy
    }
}