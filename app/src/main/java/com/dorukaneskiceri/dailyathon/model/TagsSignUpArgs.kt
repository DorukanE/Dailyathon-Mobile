package com.dorukaneskiceri.dailyathon.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class TagsSignUpArgs(
    val arrayListTags: @RawValue ArrayList<String>
): Parcelable