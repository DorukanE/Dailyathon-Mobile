package com.dorukaneskiceri.dailyathon.model

import com.google.gson.annotations.SerializedName

data class UserAnnouncementListModel(
    @SerializedName("AnnouncementID")
    val announcementID: Int,
    @SerializedName("AnnouncementContent")
    val announcementContent: String,
    @SerializedName("AnnouncementDate")
    val announcementDate: String,
    @SerializedName("AnnouncementTitle")
    val announcementTitle: String,
    @SerializedName("Visible")
    val visible: Int
)