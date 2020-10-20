package com.dorukaneskiceri.dailyathon.model.api_model

import com.google.gson.annotations.SerializedName

data class SurveyListModel(
    @SerializedName("SurveyListID")
    val surveyID: Int,
    @SerializedName("SurveyName")
    val surveyName: String,
    @SerializedName("SurveyTableName")
    val surveyTableName: String,
    @SerializedName("SurveyStartDate")
    val surveyStartDate: String,
    @SerializedName("SurveyDueDate")
    val surveyDueDate: String,
    @SerializedName("SurveyUrl")
    val surveyUrl: String,
    @SerializedName("Visible")
    val surveyVisible: Int
)